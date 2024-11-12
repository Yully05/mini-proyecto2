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

    public static void servicioVentaImpresora(Negocio local, Scanner getData, String tipoServicio){
        double cantidad, valorVenta;
        String tipoImpresion;
        System.out.print("""
                Tipo de impresion
                    (1) Color
                    (2) Blanco y negro
                Ingrese una opción:  """);
        tipoImpresion = getData.nextLine();
        if (tipoImpresion.equals("1")){
            tipoImpresion = "color";
        } else if (tipoImpresion.equals("2")){
            tipoImpresion = "BN";
        }
        System.out.print("Ingrese la cantidad: ");
        cantidad = getData.nextDouble();
        getData.nextLine(); //limpiar el salto de línea
        System.out.print("Valor pagado: ");
        valorVenta = getData.nextDouble();
        getData.nextLine();
        
        if (tipoServicio.equals("fotocopia")) {
            if (local.registrarVentaFotocopia(tipoImpresion, cantidad, valorVenta)) {
                System.out.println("\n**** Registro exitoso de fotocopia ****");
                System.out.println(tipoServicio + " - " + tipoImpresion + " cantidad: " + cantidad);
                System.out.println("Costos : " + local.getFotocopia().valorCostos() +
                                " Ganancia: " + local.getFotocopia().ganancia());
                
            } else {
                System.out.println("Error! Verifique el valor pagado.");
            }
        } else if (tipoServicio.equals("laser")) {
            if (local.registrarVentaLaser(tipoImpresion, cantidad, valorVenta)) {
                System.out.println("\n**** Registro exitoso de impresion laser ****");
                System.out.println(tipoServicio + " - " + tipoImpresion + " cantidad: " + cantidad);
                System.out.println("Costos : " + local.getLaser().valorCostos() +
                                " | Ganancia: " + local.getLaser().ganancia());
            } else {
                System.out.println("Error! Verifique el valor pagado.");
            }
        }
    }

    public static void servicioVentaPlotter(Negocio local, Scanner getData, String tipoImpresion){
        double ancho, alto, cantidad;
        double valorVenta;
        System.out.print("\nIngrese la cantidad: ");
        cantidad = getData.nextDouble();
        getData.nextLine();  //limpiar el salto de línea
        System.out.print("Ingrese el ancho en cm: ");
        ancho = getData.nextDouble();
        getData.nextLine();
        System.out.print("Ingrese el alto en cm: ");
        alto = getData.nextDouble();
        getData.nextLine();
        System.out.print("Valor pagado: ");
        valorVenta = getData.nextDouble();
        getData.nextLine();
        local.getPlotter().calcularArea(ancho, alto);
        double area = local.getPlotter().getArea();

        if (tipoImpresion == "plano" || tipoImpresion == "publicidad"){
            if (local.registrarVentaPlano(cantidad, area, valorVenta)){
                System.out.println("\n**** Registro exitoso de impresión " + tipoImpresion + " ****");
                System.out.println("Area: " + local.getPlotter().getArea() + " cm²");
                System.out.println("Costos: " + local.getPlotter().valorCostos() +
                                " | Ganancia: " + local.getPlotter().ganancia());
            } else {
                System.out.println("Error! Verifique el valor pagado.");
            }
        }
    }

    public static void cierreDia(){


    }

    public static void subMenuImpresora(Negocio local, Scanner getData){
        String opcion;
        do {
            System.out.print("""
            \nTipo de servicio:
                (1) Registrar Fotocopia
                (2) Registrar impresion Laser
                (3) Registrar impresion Plano Arquitectónico
                (4) Registrar impresion Afiche Publicitario
                (5) Menú anterior
            Ingrese una opcion: """);
            opcion = getData.nextLine();

            switch (opcion) {
                case "1" -> servicioVentaImpresora(local, getData, "fotocopia");
                case "2" -> servicioVentaImpresora(local, getData, "laser");
                case "3" -> servicioVentaPlotter(local, getData, "plano");
                case "4" -> servicioVentaPlotter(local, getData, "publicidad");
                case "5" -> { }
                default -> System.out.println("Opcion no valida");
            }
        } while (!opcion.equals("5"));
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
            Ingrese una opcion: """);
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