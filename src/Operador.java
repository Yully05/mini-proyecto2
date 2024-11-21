public class Operador {
    private String nombre;
	private TipoServicioOperador tipoServ;
    private double totalIngreso;
    private double costoUnidad;
    private double valorVentaUnidad;
    private int cantidadesVendidas;

    /*
    Las clases tipo enum nos permiten generar un conjunto de objetos preestablecidos que en este caso
    lo utilizamos dentro de la clase Operador para manejar una serie de referencias, por medio de las cuales
    se busca categorizar los servicios que el cliente vaya adquiriendo para los operadores, lo cual nos permite por
    un lado agregar más servicios a los operadores sin tener que hacer muchas modificaciones en la clase, y a su vez
    reducir la necesidad de modificar o insertar código en las clases que hagan uso de Operador.

    Lo anterior lo evidenciamos al observar que cada vez que se agrega un nuevo servicio, nos tocaba agregar los
    atributos respectivos a costos, cantidad y valor de venta, asi como su respectivos getters, setters, y
    modificaciones en otros métodos que implicaran el uso de estos campos, como lo es en la clase principal main; donde
    cada vez que se deseaba conocer algún valor de los servicios contenidos en operador, se debia insertar más condicionales
    para conocer el valor de este servicio según el operador, y así mismo, en la clase negocio al momento de hacer el registro
    de la venta, nos tocaba agregar más condicionales.

    Con la investigación que realizamos nos dimos cuenta que la mejor opción para mejorar la escalabilidad del aplicativo
    sería con los enums, ya que si realizabamos herencia, lo cual era una opción, la aplicación perdería flexibilidad al
    tener que estar declarando una subclase nueva cada vez que se agrega un servicio al Operador.

    */

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
        return this.nombre + " - "+ this.tipoServ.toString().toLowerCase()
;
    }

}