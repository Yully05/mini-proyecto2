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
		double fotocopiaTotalIngresos = 0.0;
        double fotocopiaTotalCostos = 0.0;
        double fotocopiaGanacias = 0.0;
        double laserTotalIngresos = 0.0;
        double laserTotalCostos = 0.0;
        double laserGanacias = 0.0;
        double plotterTotalIngresos = 0.0;
        double plotterTotalCostos = 0.0;
        double plotterGanacias = 0.0;
        double minutoIngresos = 0.0;
        double minutoCostos = 0.0;
        double minutoGanacias = 0.0;
        double simcardIngresos = 0.0;
        double simcardCostos = 0.0;
        double simcardGanacias = 0.0;

        Object servicioConMayorGanacias = null;
        double ganaciaMax = 0.0;

        ArrayList<Operador> operadores = local.getOperadores();
        ArrayList<ServicioImpresora> servImpresoras = local.getServicioImpresora();

		for (ServicioImpresora servimp : local.getServicioImpresora()) {
            if(servimp instanceof Impresora
                    && servimp.getTipo().contains("Fotocopia")){
                fotocopiaTotalCostos += servimp.valorCostos();
                fotocopiaTotalIngresos += servimp.getTotalIngresos();
                fotocopiaGanacias += servimp.ganancia();
            }else if(servimp instanceof Impresora
                    && servimp.getTipo().contains("Laser")){
                laserTotalCostos += servimp.valorCostos();
                laserTotalIngresos += servimp.getTotalIngresos();
                laserGanacias += servimp.ganancia();
            }else if(servimp instanceof Plotter){
                plotterTotalCostos += servimp.valorCostos();
                plotterTotalIngresos += servimp.getTotalIngresos();
                plotterGanacias += servimp.ganancia();
            }
            if(servimp.ganancia() > ganaciaMax){
                ganaciaMax = servimp.ganancia();
                servicioConMayorGanacias = servimp;
            }
        }

        for (Operador serOperador : local.getOperadores()) {
            if(serOperador.getTipoServ().toString().equals("MINUTO")){
                minutoCostos += serOperador.valorCostos();
                minutoIngresos += serOperador.getTotalIngreso();
                minutoGanacias += serOperador.ganancia();
            }else if(serOperador.getTipoServ().toString().equals("SIMCARD")){
                simcardCostos += serOperador.valorCostos();
                simcardIngresos += serOperador.getTotalIngreso();
                simcardGanacias += serOperador.ganancia();
            }
            if(serOperador.ganancia() > ganaciaMax){
                ganaciaMax = serOperador.ganancia();
                servicioConMayorGanacias = serOperador;
            }
        }

        double totalDineroRecolectadoDia = fotocopiaTotalIngresos
                + laserTotalIngresos
                + plotterTotalIngresos
                + minutoIngresos
                + simcardIngresos;
        double totalCostosOperacionales = fotocopiaTotalCostos
                + laserTotalCostos
                + plotterTotalCostos
                + minutoCostos
                + simcardCostos
                + local.getCostoEmpleadoDia()
                + local.getCostoEnergiaDia();
        double totalganaciasOperacionales = fotocopiaGanacias
                + laserGanacias
                + plotterGanacias
                + minutoGanacias
                + simcardGanacias;


        System.out.println("El Total del Dinero Recogido es: $" + totalDineroRecolectadoDia);
        System.out.println("La Ganancia Obtenida es: $" + totalganaciasOperacionales);
        System.out.println("Los Gastos de Produccion son: $" + totalCostosOperacionales );
        System.out.println("El servicio que genera mas ganancia es "
                + ((servicioConMayorGanacias == null) ? "-Ningun servicio ha sido facturado-" : servicioConMayorGanacias.toString())
                + " con $"
                + ganaciaMax);

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