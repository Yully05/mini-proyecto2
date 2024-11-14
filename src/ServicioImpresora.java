
public abstract class ServicioImpresora {
    protected double TotalIngresos;
    protected String tipo;
    protected double costo;
    protected double valorParaVenta;


    public ServicioImpresora(String tipo, double costo, double valorParaVenta) {
        this.tipo = tipo;
        this.costo = costo;
        this.valorParaVenta = valorParaVenta;
    }

    public double getTotalIngresos() {
        return TotalIngresos;
    }

    public void setTotalIngresos(double totalIngresos) {
        this.TotalIngresos += totalIngresos;
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
        return TotalIngresos - valorCostos();
    };

    public abstract double valorCostos();

}
