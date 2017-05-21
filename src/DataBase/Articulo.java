/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
    , @NamedQuery(name = "Articulo.findById", query = "SELECT a FROM Articulo a WHERE a.id = :id")
    , @NamedQuery(name = "Articulo.findByActivo", query = "SELECT a FROM Articulo a WHERE a.activo = :activo")
    , @NamedQuery(name = "Articulo.findByCodigo", query = "SELECT a FROM Articulo a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Articulo.findByNombre", query = "SELECT a FROM Articulo a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Articulo.findByDescripcion", query = "SELECT a FROM Articulo a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Articulo.findByPrecio", query = "SELECT a FROM Articulo a WHERE a.precio = :precio")
    , @NamedQuery(name = "Articulo.findBySMaximo", query = "SELECT a FROM Articulo a WHERE a.sMaximo = :sMaximo")
    , @NamedQuery(name = "Articulo.findBySMinimo", query = "SELECT a FROM Articulo a WHERE a.sMinimo = :sMinimo")
    , @NamedQuery(name = "Articulo.findByExistencia", query = "SELECT a FROM Articulo a WHERE a.existencia = :existencia")
    , @NamedQuery(name = "Articulo.findByFechaCreacion", query = "SELECT a FROM Articulo a WHERE a.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Articulo.findByFechaMod", query = "SELECT a FROM Articulo a WHERE a.fechaMod = :fechaMod")})
public class Articulo implements Serializable {

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
    private int codigo;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Precio")
    private BigDecimal precio;
    @Basic(optional = false)
    @Column(name = "SMaximo")
    private int sMaximo;
    @Basic(optional = false)
    @Column(name = "SMinimo")
    private int sMinimo;
    @Basic(optional = false)
    @Column(name = "Existencia")
    private int existencia;
    @Basic(optional = false)
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "FechaMod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDArticulo")
    private Collection<ArticuloLote> articuloLoteCollection;
    @JoinColumn(name = "IDUnidad", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ArticuloUnidad iDUnidad;

    public Articulo() {
    }

    public Articulo(Integer id) {
        this.id = id;
    }

    public Articulo(Integer id, short activo, int codigo, String nombre, String descripcion, BigDecimal precio, int sMaximo, int sMinimo, int existencia, Date fechaCreacion, Date fechaMod) {
        this.id = id;
        this.activo = activo;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.sMaximo = sMaximo;
        this.sMinimo = sMinimo;
        this.existencia = existencia;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getSMaximo() {
        return sMaximo;
    }

    public void setSMaximo(int sMaximo) {
        this.sMaximo = sMaximo;
    }

    public int getSMinimo() {
        return sMinimo;
    }

    public void setSMinimo(int sMinimo) {
        this.sMinimo = sMinimo;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
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

    @XmlTransient
    public Collection<ArticuloLote> getArticuloLoteCollection() {
        return articuloLoteCollection;
    }

    public void setArticuloLoteCollection(Collection<ArticuloLote> articuloLoteCollection) {
        this.articuloLoteCollection = articuloLoteCollection;
    }

    public ArticuloUnidad getIDUnidad() {
        return iDUnidad;
    }

    public void setIDUnidad(ArticuloUnidad iDUnidad) {
        this.iDUnidad = iDUnidad;
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
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataBase.Articulo[ id=" + id + " ]";
    }
    
}
