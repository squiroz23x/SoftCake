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
@Table(name = "venta_mp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaMp.findAll", query = "SELECT v FROM VentaMp v")
    , @NamedQuery(name = "VentaMp.findById", query = "SELECT v FROM VentaMp v WHERE v.id = :id")
    , @NamedQuery(name = "VentaMp.findByCodigo", query = "SELECT v FROM VentaMp v WHERE v.codigo = :codigo")
    , @NamedQuery(name = "VentaMp.findByDescripcion", query = "SELECT v FROM VentaMp v WHERE v.descripcion = :descripcion")
    , @NamedQuery(name = "VentaMp.findByFechaCreacion", query = "SELECT v FROM VentaMp v WHERE v.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "VentaMp.findByFechaMod", query = "SELECT v FROM VentaMp v WHERE v.fechaMod = :fechaMod")})
public class VentaMp implements Serializable {

    @Basic(optional = false)
    @Column(name = "Activo")
    private int activo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "FechaMod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDVentaMP")
    private Collection<VentaPago> ventaPagoCollection;

    public VentaMp() {
    }

    public VentaMp(Integer id) {
        this.id = id;
    }

    public VentaMp(Integer id,Integer activo, String codigo, String descripcion, Date fechaCreacion, Date fechaMod) {
        this.id = id;
        this.activo = activo;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaMod = fechaMod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    public Collection<VentaPago> getVentaPagoCollection() {
        return ventaPagoCollection;
    }

    public void setVentaPagoCollection(Collection<VentaPago> ventaPagoCollection) {
        this.ventaPagoCollection = ventaPagoCollection;
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
        if (!(object instanceof VentaMp)) {
            return false;
        }
        VentaMp other = (VentaMp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataBase.VentaMp[ id=" + id + " ]";
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
}
