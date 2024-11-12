public class Impresora extends ServicioImpresora {
    private int cantidadBN;
    private int cantidadColor;
    private double costoBN;
    private double costoColor;
    private double valorVentaBN;
    private double valorVentaColor;

    public Impresora(double costoBN, double valorVentaBN, double costoColor, double valorVentaColor){
        this.costoBN = costoBN;
        this.valorVentaBN = valorVentaBN;
        this.costoColor = costoColor;
        this.valorVentaColor = valorVentaColor;
        this.cantidadBN = 0;
        this.cantidadColor = 0;
    }

    public int getCantidadBN() {
        return cantidadBN;
    }

    public void setCantidadBN(double cantidad) {
        this.cantidadBN += cantidad;
    }

    public int getCantidadColor() {
        return cantidadColor;
    }

    public void setCantidadColor(double cantidad) {
        this.cantidadColor += cantidad;
    }

    public double getCostoBN() {
        return costoBN;
    }

    public void setCostoBN(double costoBN) {
        this.costoBN = costoBN;
    }

    public double getCostoColor() {
        return costoColor;
    }

    public void setCostoColor(double costoColor) {
        this.costoColor = costoColor;
    }

    public double getValorVentaBN() {
        return valorVentaBN;
    }

    public void setValorVentaBN(double valorVentaBN) {
        this.valorVentaBN = valorVentaBN;
    }

    public double getValorVentaColor() {
        return valorVentaColor;
    }

    public void setValorVentaColor(double valorVentaColor) {
        this.valorVentaColor = valorVentaColor;
    }

    @Override
    public double valorCostos() {
        return this.cantidadColor * this.costoColor + this.cantidadBN * this.costoBN;
    }

    @Override
    public double valorVentas(){
        return this.cantidadColor * this.valorVentaColor + this.cantidadBN * this.valorVentaColor;
    }

    @Override
    public double ganancia(){
        double ganancia;
        ganancia = this.valorPagado - this.cantidadColor * this.costoColor - this.cantidadBN * this.costoBN;
        return ganancia;
    }
}
