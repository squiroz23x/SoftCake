/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sergi
 */
@Entity
@Table(name = "articulo_lote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArticuloLote.findAll", query = "SELECT a FROM ArticuloLote a")
    , @NamedQuery(name = "ArticuloLote.findById", query = "SELECT a FROM ArticuloLote a WHERE a.id = :id")
    , @NamedQuery(name = "ArticuloLote.findByActivo", query = "SELECT a FROM ArticuloLote a WHERE a.activo = :activo")
    , @NamedQuery(name = "ArticuloLote.findByCodigo", query = "SELECT a FROM ArticuloLote a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "ArticuloLote.findByCantidad", query = "SELECT a FROM ArticuloLote a WHERE a.cantidad = :cantidad")
    , @NamedQuery(name = "ArticuloLote.findByFechaElaboracion", query = "SELECT a FROM ArticuloLote a WHERE a.fechaElaboracion = :fechaElaboracion")
    , @NamedQuery(name = "ArticuloLote.findByFecbaCaducidad", query = "SELECT a FROM ArticuloLote a WHERE a.fecbaCaducidad = :fecbaCaducidad")
    , @NamedQuery(name = "ArticuloLote.findByFechaCreacion", query = "SELECT a FROM ArticuloLote a WHERE a.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "ArticuloLote.findByFechaMod", query = "SELECT a FROM ArticuloLote a WHERE a.fechaMod = :fechaMod")})
public class ArticuloLote implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDArticuloLote")
    private Collection<VentaConcepto> ventaConceptoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Activo")
    private short activo;
    @Basic(optional = false)
    @Column(name = "Codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "Cantidad")
    private long cantidad;
    @Basic(optional = false)
    @Column(name = "FechaElaboracion")
    @Temporal(TemporalType.DATE)
    private Date fechaElaboracion;
    @Basic(optional = false)
    @Column(name = "FecbaCaducidad")
    @Temporal(TemporalType.DATE)
    private Date fecbaCaducidad;
    @Basic(optional = false)
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "FechaMod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;
    @JoinColumn(name = "IDArticulo", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Articulo iDArticulo;

    public ArticuloLote() {
    }

    public ArticuloLote(Integer id) {
        this.id = id;
    }

    public ArticuloLote(Integer id, short activo, String codigo, long cantidad, Date fechaElaboracion, Date fecbaCaducidad, Date fechaCreacion, Date fechaMod) {
        this.id = id;
        this.activo = activo;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.fechaElaboracion = fechaElaboracion;
        this.fecbaCaducidad = fecbaCaducidad;
        this.fechaCreacion = fechaCreacion;
        this.fechaMod = fechaMod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public Date getFecbaCaducidad() {
        return fecbaCaducidad;
    }

    public void setFecbaCaducidad(Date fecbaCaducidad) {
        this.fecbaCaducidad = fecbaCaducidad;
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

    public Articulo getIDArticulo() {
        return iDArticulo;
    }

    public void setIDArticulo(Articulo iDArticulo) {
        this.iDArticulo = iDArticulo;
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
        if (!(object instanceof ArticuloLote)) {
            return false;
        }
        ArticuloLote other = (ArticuloLote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataBase.ArticuloLote[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<VentaConcepto> getVentaConceptoCollection() {
        return ventaConceptoCollection;
    }

    public void setVentaConceptoCollection(Collection<VentaConcepto> ventaConceptoCollection) {
        this.ventaConceptoCollection = ventaConceptoCollection;
    }
    
}
