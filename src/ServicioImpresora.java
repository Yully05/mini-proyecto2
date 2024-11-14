
public abstract class ServicioImpresora {
    protected double totalIngresos;
    protected String tipo;
    protected double costo;
    protected double valorParaVenta;


    public ServicioImpresora(String tipo, double costo, double valorParaVenta) {
        this.tipo = tipo;
        this.costo = costo;
        this.valorParaVenta = valorParaVenta;
    }

    public double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos += totalIngresos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getValorParaVenta() {
        return valorParaVenta;
    }

    public void setValorParaVenta(double valorParaVenta) {
        this.valorParaVenta = valorParaVenta;
    }

    public double ganancia(){
        return totalIngresos - valorCostos();
    };

    public abstract double valorCostos();

}
