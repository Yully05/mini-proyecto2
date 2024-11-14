public class Impresora extends ServicioImpresora {
    protected int cantidadUnidades;
    public Impresora(String tipo, double costo, double valorParaVenta) {
        super(tipo, costo, valorParaVenta);
    }

    public int getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCantidadUnidades(int cantidadUnidades) {
        this.cantidadUnidades += cantidadUnidades;
    }

    @Override
    public double valorCostos() {
        return this.cantidadUnidades * super.getCosto();
    }
}
