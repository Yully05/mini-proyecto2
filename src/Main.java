import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void servicioVentaOperador(Negocio local, Scanner getData, String tipoVenta){
        int opcion, cantidad;
        double valorVenta;

        System.out.println("\nOperadores");
        for (int i = 0; i < local.getOperadores().size(); i++) {
            System.out.println("    (" + (i + 1) + ") " + local.getOperadores().get(i).getNombre());
        }
        System.out.print("Ingrese su opcion: ");
        opcion = getData.nextInt();
        getData.nextLine(); //limpiar el salto de línea
        Operador operador = local.getOperadores().get(opcion - 1); //opreador seleccionado
        double valorUnidad = 0;
        if (tipoVenta.equals("minuto")){
            valorUnidad= operador.getValorVentaMinuto();
        }else if (tipoVenta.equals("simcard")){
            valorUnidad = operador.getValorVentaSimCard();
        }
        System.out.println("\nValor venta por " + tipoVenta + " " + operador.getNombre() + ": " + valorUnidad);
        System.out.print("Cantidad minutos: ");
        cantidad = getData.nextInt();
        getData.nextLine(); //limpiar el salto de línea
        System.out.print("Valor pagado: ");
        valorVenta = getData.nextInt();
        getData.nextLine();

        if (local.registrarVentaOperador(opcion, tipoVenta, cantidad, valorVenta)){
            System.out.println("\n**** Registro exitoso **** ");
            if (tipoVenta.equals("minuto")){
                System.out.println(tipoVenta + " vendidos: " + operador.getCantidadMinuto());
                System.out.println("Costos: " + operador.valorCostosMinutosVendidos() +
                            " Ganancia: " + operador.gananciaMinutos());
            }else if (tipoVenta.equals("simcard")){
                System.out.println(tipoVenta + " vendidas: " + operador.getCantidadSimCard());
                System.out.println(("Costos: " + operador.valorCostoSimCardVendidas() +
                            " Ganancia: " + operador.gananciaSimCard()));
            }
        }else{
            System.out.println("Error! Verifique el valor pagado.");
        }
    }

    public static void ventaServicioImpresora(Negocio local, Scanner getData, int tipoServicio){
        double cantidad = 0, valorVenta;
        boolean confirmacionPago = false;
        try{
            if(local.getServicioImpresora().get(tipoServicio) instanceof Impresora){
                System.out.print("Ingrese la cantidad de unidades vendidas: ");
                int und = getData.nextInt();
                getData.nextLine();
                System.out.print("Valor pagado: ");
                valorVenta = getData.nextDouble();
                getData.nextLine();
                confirmacionPago = local.registrarVentaServImpresion(tipoServicio, und, valorVenta);
                cantidad = und;
            }else if(local.getServicioImpresora().get(tipoServicio) instanceof Plotter){
                System.out.print("Ingrese la cantidad de cm cuadrados vendidas (usar , para el decimal): ");
                cantidad = getData.nextDouble();
                System.out.print("Valor pagado: ");
                valorVenta = getData.nextDouble();
                confirmacionPago = local.registrarVentaServImpresion(tipoServicio, cantidad, valorVenta);
            }

           if (confirmacionPago) {
               ServicioImpresora serv = local.getServicioImpresora().get(tipoServicio);
               System.out.println("\n**** Registro exitoso de fotocopia ****");
               System.out.println(serv.getTipo()
                            + "\n cantidad: " + cantidad
                            + "\n valor pagado: " + cantidad*serv.getValorParaVenta());

           } else {
                    System.out.println("Error! Verifique el valor pagado.");
           }
        }catch (IllegalArgumentException | NoSuchElementException | IllegalStateException e){
            System.err.println("Se ha ingresado un valor incorrectamente, se cancela la operacióm " +
                    "intente nuevamente");
            if(getData.hasNext()){
                getData.nextLine();
            }
        }



    }



    public static void cierreDia(){


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
                (1) Registrar Minutos
                (2) Registrar Sim Card
                (3) Registrar servicio Impresora
                (4) Cierre dia
                (5) Salir
            Ingrese una opcion:\t""");
            opcion = getData.nextLine();

            switch (opcion) {
                case "1" -> servicioVentaOperador(local, getData, "minuto");
                case "2" -> servicioVentaOperador(local, getData, "simcard");
                case "3" -> subMenuImpresora(local, getData);
                case "4" -> cierreDia();
                case "5" -> { }
                default -> System.out.println("Opcion no valida");
            }
        } while (!opcion.equals("5"));

        System.out.println("***** Gracias por utilizar nuestros servicios *****");
    }
}