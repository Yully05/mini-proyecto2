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
        return CmCuadradoFacturados * super.getCosto();
    }

}
