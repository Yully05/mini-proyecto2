public class Operador {
    private String nombre;
    private double valorPagado;
    private double costoMinuto;
    private double valorVentaMinuto;
    private double cantidadMinuto;
    private double costoSimCard;
    private double valorVentaSimCard;
    private double cantidadSimCard;

    public Operador(String nombre, double costoMin, double ventaMin, double costoSim, double ventaSim){
        this.nombre = nombre;
        this.costoMinuto = costoMin;
        this.valorVentaMinuto = ventaMin;
        this.costoSimCard = costoSim;
        this.valorVentaSimCard = ventaSim;
        this.cantidadMinuto = 0;
        this.cantidadSimCard = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    public double getCostoMinuto() {
        return costoMinuto;
    }

    public void setCostoMinuto(double costo) {
        this.costoMinuto = costo;
    }

    public double getValorVentaMinuto() {
        return valorVentaMinuto;
    }

    public void setValorVentaMinuto(double valorVenta) {
        this.valorVentaMinuto = valorVenta;
    }

    public double getCantidadMinuto() {
        return cantidadMinuto;
    }

    public void setCantidadMinuto(double cantidadMin) {
        this.cantidadMinuto = this.cantidadMinuto + cantidadMin;
    }

    public double getCostoSimCard() {
        return costoSimCard;
    }

    public void setCostoSimCard(double costoSimCard) {
        this.costoSimCard = costoSimCard;
    }

    public double getValorVentaSimCard() {
        return valorVentaSimCard;
    }

    public void setValorVentaSimCard(double valorVentaSimCard) {
        this.valorVentaSimCard = valorVentaSimCard;
    }

    public double getCantidadSimCard() {
        return cantidadSimCard;
    }

    public void setCantidadSimCard(double cantidadSim) {
        this.cantidadSimCard = this.cantidadSimCard + cantidadSim;
    }

    public double gananciaMinutos(){
        return this.cantidadMinuto * (this.valorVentaMinuto - this.costoMinuto);
    }

    public double valorCostosMinutosVendidos(){
        return this.cantidadMinuto * this.costoMinuto;
    }

    public double valorVentasMinutosVendidos(){
        return this.cantidadMinuto * this.valorVentaMinuto;
    }

    public double gananciaSimCard(){
        return this.cantidadSimCard * (this.valorVentaSimCard - this.costoSimCard);
    }

    public double valorCostoSimCardVendidas(){
        return this.getCantidadSimCard() * this.costoSimCard;
    }

    public double valorVentasSimCardVendidas(){
        return this.cantidadSimCard * this.valorVentaSimCard;
    }

}