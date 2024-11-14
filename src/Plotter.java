public class Plotter extends ServicioImpresora{
    private double CmCuadradoFacturados;

    public Plotter(String tipo, double costo, double valorParaVenta) {
        super(tipo, costo, valorParaVenta);
    }

    public double getCmCuadradoFacturados() {
        return CmCuadradoFacturados;
    }

    public void setCmCuadradoFacturados(double cmCuadradoFacturados) {
        CmCuadradoFacturados += cmCuadradoFacturados;
    }

    @Override
    public double valorCostos() {
        return Math.round((CmCuadradoFacturados * super.getCosto() * 100.0) / 100.0);
    }
	
	@Override
	public String toString() {
		return this.tipo + " - cm cuadrados vendidos: " + this.CmCuadradoFacturados + " - Total ingresos = $" + super.getTotalIngresos();
	}

}
