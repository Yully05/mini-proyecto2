import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void servicioVentaOperador(Negocio local, Scanner getData){
        int cantidad;
        String opcion;
        double valorVenta;
        ArrayList<Operador> operadores = local.getOperadores();
		while(true){
            System.out.println("\nServicios operadores");
            for (int i = 0; i < operadores.size(); i++) {
                System.out.println("    (" + (i + 1) + ") "
                        + operadores.get(i).getNombre()
                        + " - "
                        + operadores.get(i).getTipoServ().toString().toLowerCase());
            }
            System.out.print("""
            \n(M) Menú anterior
            Ingrese una opcion:\t""");
            opcion = getData.nextLine();

            if (opcion.equals("M")){break;}

            try {
                int valorOpcion = Integer.parseInt(opcion) - 1;
                if(valorOpcion < operadores.size()
                        && valorOpcion >= 0){

                    System.out.println("\nValor venta por " + operadores.get(valorOpcion).getTipoServ().toString().toLowerCase()
                            + " " + operadores.get(valorOpcion).getNombre() + ": "
                            + operadores.get(valorOpcion).getValorVentaUnidad());
                    System.out.print("Ingrese la cantidad: ");
                    cantidad = getData.nextInt();
                    getData.nextLine(); //limpiar el salto de línea
                    System.out.print("Valor pagado: ");
                    valorVenta = getData.nextDouble();
                    getData.nextLine();


                    if (local.registrarVentaOperador(valorOpcion, cantidad, valorVenta)){
                        System.out.printf("\n**** Registro exitoso de %s - %s**** \n cantidad: %d \n valor pagado: %.2f",
                                operadores.get(valorOpcion).getNombre(),
                                operadores.get(valorOpcion).getTipoServ().toString().toLowerCase(),
                                cantidad,
                                valorVenta);

                    }else{
                        System.out.println("Error! Verifique el valor pagado.");
                    }
                    break;
                }else{
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e){
                System.err.println("\nError! la opción ingresada no es correcta\n");
            }catch (IllegalArgumentException | NoSuchElementException | IllegalStateException e){
                System.err.println("Se ha ingresado un valor incorrectamente, se cancela la operación" +
                        "intente nuevamente");
                if(getData.hasNext()){
                    getData.nextLine();
                }
            }
        }



    }

    public static void ventaServicioImpresora(Negocio local, Scanner getData, int tipoServicio){
        double cantidad = 0.0, valorVenta = 0.0;
        boolean confirmacionPago = false;
        try{
			ServicioImpresora serv = local.getServicioImpresora().get(tipoServicio);
            if(serv instanceof Impresora){
                System.out.print("Ingrese la cantidad de unidades vendidas: ");
                int und = getData.nextInt();
                getData.nextLine();
                System.out.print("Valor pagado: ");
                valorVenta = getData.nextDouble();
                getData.nextLine();
                confirmacionPago = local.registrarVentaServImpresion(tipoServicio, und, valorVenta);
                cantidad = und;
            }else if(serv instanceof Plotter){
                System.out.print("Ingrese alto en cm cuadrados del encargo (usar , para el decimal): ");
                double alto = getData.nextDouble();
				getData.nextLine();
				System.out.print("Ingrese ancho en cm cuadrados del encargo (usar , para el decimal): ");
                double ancho = getData.nextDouble();
				getData.nextLine();
				cantidad = Negocio.aDosDecimales(alto*ancho);
                System.out.println("Valor a pagar $" + Negocio.aDosDecimales(serv.getValorParaVenta() * cantidad));
                System.out.print("Valor pagado: ");
				valorVenta = getData.nextDouble();
				getData.nextLine();
                confirmacionPago = local.registrarVentaServImpresion(tipoServicio, cantidad, valorVenta);
            }

           if (confirmacionPago) {
               System.out.printf("\n**** Registro exitoso de %s**** \n cantidad: %.2f \n valor pagado: %.2f",
                       serv.getTipo(),
                       cantidad,
                       valorVenta);
           } else {
                    System.out.println("Error! Verifique el valor pagado.");
           }
        }catch (IllegalArgumentException | NoSuchElementException | IllegalStateException e){
            System.err.println("Se ha ingresado un valor incorrectamente, se cancela la operación" +
                    "intente nuevamente");
            if(getData.hasNext()){
                getData.nextLine();
            }
        }
    }



    public static void cierreDia(Negocio local){
		
		
		for (int i = 0; i < local.getServicioImpresora().size(); i++) {
            System.out.println(local.getServicioImpresora().get(i));
        }

        for (int i = 0; i < local.getOperadores().size(); i++) {
            System.out.println(local.getOperadores().get(i));
        }
    }

    public static void subMenuImpresora(Negocio local, Scanner getData){
        String opcion;
        int cantidadServimpresion = local.getServicioImpresora().size();
        do {
            System.out.print("\nTipo de servicio:");
            for (int i = 0; i < cantidadServimpresion; i++) {
                System.out.printf("\n(%d) Registrar %s", 1+i, local.getServicioImpresora().get(i).getTipo());
            }
            System.out.print("""
                \n(M) Menú anterior
                Ingrese una opcion:\t""");
            opcion = getData.nextLine();

            if (opcion.equals("M")){break;}

            try {
                int valorOpcion = Integer.parseInt(opcion) - 1;
                if(valorOpcion < cantidadServimpresion
                        && valorOpcion >= 0){
                    ventaServicioImpresora(local, getData, valorOpcion);
                    break;
                }else{
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e){
                System.err.println("\nError! la opción ingresada no es correcta\n");
            }
        } while (true);
    }

    public static void main(String[] args) throws Exception {

        Negocio local = new Negocio(20000, 30000);
        Scanner getData = new Scanner(System.in);
        String opcion;

        System.out.println("\n***** Bienvenido a Variedades Tecnológicas ******");
        do {
            System.out.print("""
            \nFunciones:
                (1) Registrar servicio de Operador
                (2) Registrar servicio Impresora
                (3) Cierre dia
                (4) Salir
            Ingrese una opcion:\t""");
            opcion = getData.nextLine();

            switch (opcion) {
                case "1" -> servicioVentaOperador(local, getData);
                case "2" -> subMenuImpresora(local, getData);
                case "3" -> cierreDia(local);
                case "4" -> { }
                default -> System.out.println("Opcion no valida");
            }
        } while (!opcion.equals("4"));

        System.out.println("***** Gracias por utilizar nuestros servicios *****");
    }
}