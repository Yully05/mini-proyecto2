public class Plotter extends ServicioImpresora{
    private double alto;
    private double ancho;
    private double area;
    private double costoPlano;
    private double costoPublicidad;
    private double valorVentaPlano;
    private double valorVentaPublicidad;

    public Plotter(double costoPlano, double costoPublicidad, double ventaPlano, double ventaPublicidad){
        this.costoPlano = costoPlano;
        this.costoPublicidad = costoPublicidad;
        this.valorVentaPlano = ventaPlano;
        this.valorVentaPublicidad = ventaPublicidad;
        this.ancho = 0;
        this.alto = 0;
        this. area = 0;
    }

    public void setCantidad(double cantidad) {
        this.cantidad += cantidad;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double largo) {
        this.alto = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getCostoPlano() {
        return costoPlano;
    }

    public void setCostoPlano(double costoPlano) {
        this.costoPlano = costoPlano;
    }

    public double getCostoPublicidad() {
        return costoPublicidad;
    }

    public void setCostoPublicidad(double costoPublicidad) {
        this.costoPublicidad = costoPublicidad;
    }

    public double getValorVentaPlano() {
        return valorVentaPlano;
    }

    public void setValorVentaPlano(double valorVentaPlano) {
        this.valorVentaPlano = valorVentaPlano;
    }

    public double getValorVentaPublicidad() {
        return valorVentaPublicidad;
    }

    public void setValorVentaPublicidad(double valorVentaPublicidad) {
        this.valorVentaPublicidad = valorVentaPublicidad;
    }

    public void calcularArea(double ancho, double alto){
        this.area = ancho * alto;
    }

    @Override

    public double valorCostos() {
        if (tipoImpresion.equals("plano")) {
            return this.area * this.costoPlano;
        } else if (tipoImpresion.equals("publicidad")) {
            return this.area * this.costoPublicidad;
        }
        return 0;
    }

    @Override
    public double valorVentas() {
        if (tipoImpresion.equals("plano")) {
            return this.area * this.valorVentaPlano;
        } else if (tipoImpresion.equals("publicidad")) {
            return this.area * this.valorVentaPublicidad;
        }
        return 0;
    }

    @Override
    public double ganancia() {
        return valorVentas() - valorCostos();
    }

}
