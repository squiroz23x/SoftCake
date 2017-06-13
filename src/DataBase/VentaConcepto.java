/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sergi
 */
@Entity
@Table(name = "venta_concepto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaConcepto.findAll", query = "SELECT v FROM VentaConcepto v")
    , @NamedQuery(name = "VentaConcepto.findById", query = "SELECT v FROM VentaConcepto v WHERE v.id = :id")
    , @NamedQuery(name = "VentaConcepto.findByActivo", query = "SELECT v FROM VentaConcepto v WHERE v.activo = :activo")
    , @NamedQuery(name = "VentaConcepto.findByProdCodigo", query = "SELECT v FROM VentaConcepto v WHERE v.prodCodigo = :prodCodigo")
    , @NamedQuery(name = "VentaConcepto.findByCantidad", query = "SELECT v FROM VentaConcepto v WHERE v.cantidad = :cantidad")
    , @NamedQuery(name = "VentaConcepto.findByDrescripcion", query = "SELECT v FROM VentaConcepto v WHERE v.drescripcion = :drescripcion")
    , @NamedQuery(name = "VentaConcepto.findByPrecioUnitario", query = "SELECT v FROM VentaConcepto v WHERE v.precioUnitario = :precioUnitario")
    , @NamedQuery(name = "VentaConcepto.findByFechaCreacion", query = "SELECT v FROM VentaConcepto v WHERE v.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "VentaConcepto.findByFechaMod", query = "SELECT v FROM VentaConcepto v WHERE v.fechaMod = :fechaMod")})
public class VentaConcepto implements Serializable {

    @JoinColumn(name = "IDArticulo", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Articulo iDArticulo;

    @JoinColumn(name = "IDArticulo_Lote", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ArticuloLote iDArticuloLote;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Activo")
    private int activo;
    @Basic(optional = false)
    @Column(name = "ProdCodigo")
    private String prodCodigo;
    @Basic(optional = false)
    @Column(name = "Cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "Drescripcion")
    private String drescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PrecioUnitario")
    private BigDecimal precioUnitario;
    @Basic(optional = false)
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "FechaMod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;
    @JoinColumn(name = "IDVenta", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Venta iDVenta;

    public VentaConcepto() {
    }

    public VentaConcepto(Integer id) {
        this.id = id;
    }

    public VentaConcepto(Integer id, int activo, String prodCodigo, int cantidad, String drescripcion, BigDecimal precioUnitario, Date fechaCreacion, Date fechaMod) {
        this.id = id;
        this.activo = activo;
        this.prodCodigo = prodCodigo;
        this.cantidad = cantidad;
        this.drescripcion = drescripcion;
        this.precioUnitario = precioUnitario;
        this.fechaCreacion = fechaCreacion;
        this.fechaMod = fechaMod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getProdCodigo() {
        return prodCodigo;
    }

    public void setProdCodigo(String prodCodigo) {
        this.prodCodigo = prodCodigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDrescripcion() {
        return drescripcion;
    }

    public void setDrescripcion(String drescripcion) {
        this.drescripcion = drescripcion;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Date fechaMod) {
        this.fechaMod = fechaMod;
    }

    public Venta getIDVenta() {
        return iDVenta;
    }

    public void setIDVenta(Venta iDVenta) {
        this.iDVenta = iDVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaConcepto)) {
            return false;
        }
        VentaConcepto other = (VentaConcepto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataBase.VentaConcepto[ id=" + id + " ]";
    }

    public ArticuloLote getIDArticuloLote() {
        return iDArticuloLote;
    }

    public void setIDArticuloLote(ArticuloLote iDArticuloLote) {
        this.iDArticuloLote = iDArticuloLote;
    }

    public Articulo getIDArticulo() {
        return iDArticulo;
    }

    public void setIDArticulo(Articulo iDArticulo) {
        this.iDArticulo = iDArticulo;
    }
    
}
