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
@Table(name = "venta_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaPago.findAll", query = "SELECT v FROM VentaPago v")
    , @NamedQuery(name = "VentaPago.findById", query = "SELECT v FROM VentaPago v WHERE v.id = :id")
    , @NamedQuery(name = "VentaPago.findByActivo", query = "SELECT v FROM VentaPago v WHERE v.activo = :activo")
    , @NamedQuery(name = "VentaPago.findByMonto", query = "SELECT v FROM VentaPago v WHERE v.monto = :monto")
    , @NamedQuery(name = "VentaPago.findByFechaPago", query = "SELECT v FROM VentaPago v WHERE v.fechaPago = :fechaPago")
    , @NamedQuery(name = "VentaPago.findByFechaCreacion", query = "SELECT v FROM VentaPago v WHERE v.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "VentaPago.findByFechaMod", query = "SELECT v FROM VentaPago v WHERE v.fechaMod = :fechaMod")})
public class VentaPago implements Serializable {

    @Basic(optional = false)
    @Column(name = "Autorizo")
    private String autorizo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Activo")
    private int activo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Monto")
    private BigDecimal monto;
    @Basic(optional = false)
    @Column(name = "FechaPago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
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
    @JoinColumn(name = "IDVenta_MP", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private VentaMp iDVentaMP;

    public VentaPago() {
    }

    public VentaPago(Integer id) {
        this.id = id;
    }

    public VentaPago(Integer id, int activo, BigDecimal monto, String autorizo, Date fechaPago, Date fechaCreacion, Date fechaMod) {
        this.id = id;
        this.activo = activo;
        this.monto = monto;
        this.autorizo = autorizo;
        this.fechaPago = fechaPago;
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

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
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

    public VentaMp getIDVentaMP() {
        return iDVentaMP;
    }

    public void setIDVentaMP(VentaMp iDVentaMP) {
        this.iDVentaMP = iDVentaMP;
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
        if (!(object instanceof VentaPago)) {
            return false;
        }
        VentaPago other = (VentaPago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataBase.VentaPago[ id=" + id + " ]";
    }

    public String getAutorizo() {
        return autorizo;
    }

    public void setAutorizo(String autorizo) {
        this.autorizo = autorizo;
    }
    
}
