public abstract class ServicioImpresora {
    protected String tipoImpresion;
    protected double cantidad;
    protected double valorPagado;

    public ServicioImpresora(){
        this.tipoImpresion = " ";
        this.cantidad = 0;
        this.valorPagado = 0;
    }
    
    public String getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(String tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }
    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad += cantidad;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    public abstract double ganancia();

    public abstract double valorCostos();

    public abstract double valorVentas();


}
