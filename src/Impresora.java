public class Impresora extends ServicioImpresora {
    private int cantidadUnidades;
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
        return Math.round((cantidadUnidades * super.getCosto() * 100.0) / 100.0);
    }
	
	@Override
	public String toString() {
		return this.tipo;
	}
}
