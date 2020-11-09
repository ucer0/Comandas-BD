package modelos;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * @author Pablo Martínez y Santiago Ucero. Cesur 2ªDAM, Diseño de Interfaces
 */
public class Pedidos {

    private int id;
    private String bebida;
    private String comida;
    private String cliente;
    private double precio;
    private LocalDate currentDate;
    private Boolean entregado;

    public Pedidos() {
    }

    public Pedidos(int id, String bebida, String comida, String cliente, double precio, LocalDate currentDate, boolean entregado) {
        this.id = id;
        this.bebida = bebida;
        this.comida = comida;
        this.cliente = cliente;
        this.precio = precio;
        this.currentDate = currentDate;
        this.entregado = entregado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }


    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public Boolean getEntregado() {
        return entregado;
    }

    public void setEntregado(Boolean entregado) {
        this.entregado = entregado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedidos other = (Pedidos) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (!Objects.equals(this.bebida, other.bebida)) {
            return false;
        }
        if (!Objects.equals(this.comida, other.comida)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.currentDate, other.currentDate)) {
            return false;
        }
        if (!Objects.equals(this.entregado, other.entregado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pedidos{" + "id=" + id + ", bebida=" + bebida + ", comida=" + comida + ", cliente=" + cliente + ", precio=" + precio + ", currentDate=" + currentDate + ", entregado=" + entregado + '}';
    }
}
