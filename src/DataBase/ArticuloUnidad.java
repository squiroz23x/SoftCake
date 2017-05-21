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
@Table(name = "articulo_unidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArticuloUnidad.findAll", query = "SELECT a FROM ArticuloUnidad a")
    , @NamedQuery(name = "ArticuloUnidad.findById", query = "SELECT a FROM ArticuloUnidad a WHERE a.id = :id")
    , @NamedQuery(name = "ArticuloUnidad.findByActivo", query = "SELECT a FROM ArticuloUnidad a WHERE a.activo = :activo")
    , @NamedQuery(name = "ArticuloUnidad.findByDescripcion", query = "SELECT a FROM ArticuloUnidad a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "ArticuloUnidad.findByNombreCorto", query = "SELECT a FROM ArticuloUnidad a WHERE a.nombreCorto = :nombreCorto")
    , @NamedQuery(name = "ArticuloUnidad.findByFechaCreacion", query = "SELECT a FROM ArticuloUnidad a WHERE a.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "ArticuloUnidad.findByFechaMod", query = "SELECT a FROM ArticuloUnidad a WHERE a.fechaMod = :fechaMod")})
public class ArticuloUnidad implements Serializable {

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
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "NombreCorto")
    private String nombreCorto;
    @Basic(optional = false)
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "FechaMod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDUnidad")
    private Collection<Articulo> articuloCollection;

    public ArticuloUnidad() {
    }

    public ArticuloUnidad(Integer id) {
        this.id = id;
    }

    public ArticuloUnidad(Integer id, short activo, String descripcion, String nombreCorto, Date fechaCreacion, Date fechaMod) {
        this.id = id;
        this.activo = activo;
        this.descripcion = descripcion;
        this.nombreCorto = nombreCorto;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
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
    public Collection<Articulo> getArticuloCollection() {
        return articuloCollection;
    }

    public void setArticuloCollection(Collection<Articulo> articuloCollection) {
        this.articuloCollection = articuloCollection;
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
        if (!(object instanceof ArticuloUnidad)) {
            return false;
        }
        ArticuloUnidad other = (ArticuloUnidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataBase.ArticuloUnidad[ id=" + id + " ]";
    }
    
}
