package sample.Modelos;

public class ArticuloOrdenDAO {
    private int idAO;
    private int idOrden;
    private int idPlatillo;
    private float precio;

    public int getIdAO() {
        return idAO;
    }

    public void setIdAO(int idAO) {
        this.idAO = idAO;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
