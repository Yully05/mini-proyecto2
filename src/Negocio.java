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
        operadores.add(new Operador("UFF", "MINUTO", 30, 100));
		operadores.add(new Operador("UFF", "SIMCARD", 500, 1000));
		operadores.add(new Operador("Claro", "MINUTO", 70, 200));
		operadores.add(new Operador("Claro", "SIMCARD", 900, 2000));
		operadores.add(new Operador("Movistar", "MINUTO", 50, 150));
		operadores.add(new Operador("Movistar", "SIMCARD", 700, 1500));
        this.costoEnergiaDia = costoEnergia;
        this.costoEmpleadoDia = costoEmpleado;
    }

    public ArrayList<ServicioImpresora> getServicioImpresora() {
        return (ArrayList<ServicioImpresora>) servicioImpresora.clone();
    }

    public void setServicioImpresora(ArrayList<ServicioImpresora> servicioImpresora) {
        this.servicioImpresora = servicioImpresora;
    }

    public ArrayList<Operador> getOperadores() {
        return (ArrayList<Operador>) operadores.clone();
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

    public boolean registrarVentaOperador(int servOperadorSeleccionado, int cantidad, double valorPagado){
        if(cantidad > 0
                && servOperadorSeleccionado >=0
                && servOperadorSeleccionado < this.operadores.size()
                && valorPagado == cantidad * this.operadores.get(servOperadorSeleccionado).getValorVentaUnidad()){

            //Aqui se puede ver como el uso de enum en operador ha facilitado el registro de la venta, ya que
            //solo con el indice de la lista se puede completar la transacción, evitando tener que recibir el tipo de
            //servicio, e insertar condicionales para definir en qué campos se reflejaría la transacción.

            this.operadores.get(servOperadorSeleccionado).setCantidadesVendidas(cantidad);
            this.operadores.get(servOperadorSeleccionado).setTotalIngreso(valorPagado);
            return true;
        }
        return false;
    }

    public boolean registrarVentaServImpresion(int tipoImpresion, int cantidad, double valorPagado){
        if(tipoImpresion >= 0
                && tipoImpresion < this.servicioImpresora.size()
                && this.servicioImpresora.get(tipoImpresion) instanceof Impresora
                && cantidad > 0
                && valorPagado == cantidad * this.servicioImpresora.get(tipoImpresion).getValorParaVenta()){
            ((Impresora) this.servicioImpresora.get(tipoImpresion)).setCantidadUnidades(cantidad);
			this.servicioImpresora.get(tipoImpresion).setTotalIngresos(valorPagado);
            return true;
        }
        return false;
    }

    public boolean registrarVentaServImpresion(int tipoPlotter, double cantidad, double valorPagado){
		if(tipoPlotter >= 0
                && tipoPlotter < this.servicioImpresora.size()
                && this.servicioImpresora.get(tipoPlotter) instanceof Plotter
                && cantidad > 0.0
                && valorPagado == aDosDecimales(cantidad * this.servicioImpresora.get(tipoPlotter).getValorParaVenta())){
            ((Plotter) this.servicioImpresora.get(tipoPlotter)).setCmCuadradoFacturados(cantidad);
			this.servicioImpresora.get(tipoPlotter).setTotalIngresos(valorPagado);
            return true;
        }
        return false;
    }

    public static double aDosDecimales(double numDouble){
        return Math.round(numDouble*100.0)/100.0;
    }

}
