import java.util.ArrayList;

public class Negocio {
    private ArrayList<ServicioImpresora> servicioImpresora;
    private ArrayList<Operador> operadores;
    private double costoEnergiaDia;
    private double costoEmpleadoDia;

    public Negocio(double costoEnergia, double costoEmpleado){
        servicioImpresora = new ArrayList<>();
        servicioImpresora.add(new Impresora("Fotocopia-BN", 50, 100));
        servicioImpresora.add(new Impresora("Fotocopia-Color", 100, 200));
        servicioImpresora.add(new Impresora("Laser-BN", 150, 300));
        servicioImpresora.add(new Impresora("Laser-Color", 300, 500));
        servicioImpresora.add(new Plotter("Plotter-Planos", 20, 30));
        servicioImpresora.add(new Plotter("Plotter-Publicidad", 40, 60));

        operadores = new ArrayList<>();
        operadores.add(new Operador("UFF", 30, 100, 500, 1000));
        operadores.add(new Operador("Claro", 70, 200, 900, 2000));
        operadores.add(new Operador("Movistar", 50, 150, 700, 1500));
        this.costoEnergiaDia = costoEnergia;
        this.costoEmpleadoDia = costoEmpleado;
    }

    public ArrayList<ServicioImpresora> getServicioImpresora() {
        return servicioImpresora;
    }

    public void setServicioImpresora(ArrayList<ServicioImpresora> servicioImpresora) {
        this.servicioImpresora = servicioImpresora;
    }

    public ArrayList<Operador> getOperadores() {
        return operadores;
    }

    public void setOperadores(ArrayList<Operador> listaMinutos) {
        this.operadores = listaMinutos;
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
        Operador operador = this.operadores.get(indiceOperador - 1); // operador especifico segun la posicion

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

    public boolean registrarVentaServImpresion(int tipoImpresion, int cantidad, double valorPagado){
        if(tipoImpresion >= 0
                && tipoImpresion < this.servicioImpresora.size()
                && this.servicioImpresora.get(tipoImpresion) instanceof Impresora
                && cantidad > 0
                && valorPagado == cantidad * this.servicioImpresora.get(tipoImpresion).getValorParaVenta()){
            ((Impresora) this.servicioImpresora.get(tipoImpresion)).setCantidadUnidades(cantidad);
            return true;
        }
        return false;
    }

    public boolean registrarVentaServImpresion(int tipoPlotter, double cantidad, double valorPagado){
        if(tipoPlotter >= 0
                && tipoPlotter < this.servicioImpresora.size()
                && this.servicioImpresora.get(tipoPlotter) instanceof Plotter
                && cantidad > 0.0
                && valorPagado == cantidad * this.servicioImpresora.get(tipoPlotter).getValorParaVenta()){
            ((Plotter) this.servicioImpresora.get(tipoPlotter)).setCmCuadradoFacturados(cantidad);
            return true;
        }
        return false;
    }


}
