public class Operador {
    private String nombre;
	private TipoServicioOperador tipoServ;
    private double totalIngreso;
    private double costoUnidad;
    private double valorVentaUnidad;
    private int cantidadesVendidas;
	
	public enum TipoServicioOperador {
    MINUTO, SIMCARD;
	}

    public Operador(String nombre, String tipoServ, double costoUnidad, double valorVentaUnidad){
        this.nombre = nombre;
        this.tipoServ = TipoServicioOperador.valueOf(tipoServ.toUpperCase());
        this.costoUnidad = costoUnidad;
        this.valorVentaUnidad = valorVentaUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoServicioOperador getTipoServ() {
        return tipoServ;
    }

    public void setTipoServ(TipoServicioOperador tipoServ) {
        this.tipoServ = tipoServ;
    }
	
	public double getTotalIngreso() {
        return totalIngreso;
    }

    public void setTotalIngreso(double valorVenta) {
        this.totalIngreso += valorVenta;
    }

    public double getCostoUnidad() {
        return costoUnidad;
    }

    public void setCostoUnidad(double costo) {
        this.costoUnidad = costo;
    }

    public double getValorVentaUnidad() {
        return valorVentaUnidad;
    }

    public void setValorVentaUnidad(double valorVentaUnidad) {
        this.valorVentaUnidad = valorVentaUnidad;
    }

    public int getCantidadesVendidas() {
        return cantidadesVendidas;
    }

    public void setCantidadesVendidas(int cantidadesVendidas) {
        this.cantidadesVendidas += cantidadesVendidas;
    }


    public double ganancia(){
        return this.totalIngreso - valorCostos();
    }

    public double valorCostos(){
        return this.cantidadesVendidas * this.costoUnidad;
    }

    @Override
    public String toString() {
        return this.nombre
                + " - "
                + this.tipoServ.toString().toLowerCase()
                + " - unidades vendidas: "
                + this.cantidadesVendidas
                + " - Total ingresos = $"
                + this.totalIngreso;
    }

}