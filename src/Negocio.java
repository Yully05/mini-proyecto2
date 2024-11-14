import java.util.ArrayList;

public class Negocio {
    private Impresora Fotocopia;
    private Impresora Laser;
    private Plotter Plotter;
    private ArrayList<Operador> Operadores;
    private double costoEnergiaDia;
    private double costoEmpleadoDia;

    public Negocio(double costoEnergia, double costoEmpleado){
        Fotocopia = new Impresora( 50, 100, 100, 200);
        Laser = new Impresora(150, 300, 300, 500);
        Plotter = new Plotter(20, 40, 30, 60);
        Operadores = new ArrayList<>();
        Operadores.add(new Operador("UFF", 30, 100, 500, 1000));
        Operadores.add(new Operador("Claro", 70, 200, 900, 2000));
        Operadores.add(new Operador("Movistar", 50, 150, 700, 1500));
        this.costoEnergiaDia = costoEnergia;
        this.costoEmpleadoDia = costoEmpleado;
    }

    public Plotter getPlotter() {
        return Plotter;
    }

    public void setPlotter(Plotter plotter) {
        Plotter = plotter;
    }
    
    public Impresora getFotocopia() {
        return Fotocopia;
    }

    public void setFotocopia(Impresora impresora) {
        Fotocopia = impresora;
    }

    public Impresora getLaser() {
        return Laser;
    }

    public void setLaser(Impresora laser) {
        Laser = laser;
    }
    
    public ArrayList<Operador> getOperadores() {
        return Operadores;
    }

    public void setOperadores(ArrayList<Operador> listaMinutos) {
        this.Operadores = listaMinutos;
    }

    public double getCostoEnergiaDia() {
        return costoEnergiaDia;
    }

    public void setCostoEnergiaDia(double costoEnergiaDia) {
        this.costoEnergiaDia = costoEnergiaDia;
    }

    public double getCostoEmpleadoDia() {
        return costoEmpleadoDia;
    }

    public void setCostoEmpleadoDia(double costoEmpleadoDia) {
        this.costoEmpleadoDia = costoEmpleadoDia;
    }

    public boolean registrarVentaOperador(int indiceOperador, String tipoVentaOperador, double cantidad, double valorPagado){
        Operador operador = this.Operadores.get(indiceOperador - 1); // operador especifico segun la posicion

        if (tipoVentaOperador.equals("minuto")){
            double valorVentaMinuto = operador.getValorVentaMinuto(); // valor por minuto del operador especifico}
            
            if (valorPagado == cantidad * valorVentaMinuto) {
                operador.setCantidadMinuto(cantidad);
                return true;
            } else {
                return false;
            }
        } else if (tipoVentaOperador.equals("simcard")){
            double valorVentaSimCard = operador.getValorVentaSimCard(); //valor simcard operador especifico

            if (valorPagado == cantidad * valorVentaSimCard){
                operador.setCantidadSimCard(cantidad);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean registrarVentaFotocopia(String tipoImpresion, double cantidad, double valorPagado){
        if (tipoImpresion.equals("color")){
            if (valorPagado == cantidad * Fotocopia.getValorVentaColor()){
                Fotocopia.setCantidadColor(cantidad);
                Fotocopia.setValorPagado(valorPagado);
                return true;
            }
        }else if (tipoImpresion.equals("BN")){
            if (valorPagado == cantidad * Fotocopia.getValorVentaBN()){
                Fotocopia.setCantidadBN(cantidad);
                Fotocopia.setValorPagado(valorPagado);
                return true;
            }
        }
        return false;
    }

    public boolean registrarVentaLaser(String tipoImpresion, double cantidad, double valorPagado){
        if (tipoImpresion.equals("color")){
            if (valorPagado == cantidad * Laser.getValorVentaColor()){
                Laser.setCantidadColor(cantidad);
                Laser.setValorPagado(valorPagado);
                return true;
            }
        }else if (tipoImpresion.equals("BN")){
            if (valorPagado == cantidad * Laser.getValorVentaBN()){
                Laser.setCantidadBN(cantidad);
                Laser.setValorPagado(valorPagado);
                return true;
            }
        }
        return false;
    }

    public boolean registrarVentaPlano(double cantidad, double area, double valorPagado){
        if (valorPagado == cantidad * area * Plotter.getValorVentaPlano()) {
            Plotter.setCantidad(cantidad);

            return true;
        }
        return false;
    }

    public boolean registrarVentaPublicidad(double cantidad, double area, double valorPagado) {
        if (valorPagado == cantidad * area * Plotter.getValorVentaPublicidad()) {
            Plotter.setCantidad(cantidad);
            return true;
        }
        return false;
    }

    public String servicioGananciaMax(){
        double gananciaFotoc, gananciaLaser, gananciaPlotter;
        gananciaFotoc = Fotocopia.ganancia();
        gananciaLaser = Laser.ganancia();
        gananciaPlotter = Plotter.ganancia();
        if (gananciaFotoc > gananciaLaser && gananciaFotoc > gananciaPlotter){
            return String.format("Ganancia de la Fotocopiadora - %2f", gananciaFotoc);
        } else if (gananciaLaser > gananciaFotoc && gananciaLaser > gananciaPlotter) {
            return String.format("Ganancia de la Impresora - %2f", gananciaLaser);
        }else{
            return String.format("Ganancia del Plotter - %2f", gananciaPlotter);
        }
    }
}
