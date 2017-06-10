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
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
    , @NamedQuery(name = "Venta.findById", query = "SELECT v FROM Venta v WHERE v.id = :id")
    , @NamedQuery(name = "Venta.findByActivo", query = "SELECT v FROM Venta v WHERE v.activo = :activo")
    , @NamedQuery(name = "Venta.findByEstadoDoc", query = "SELECT v FROM Venta v WHERE v.estadoDoc = :estadoDoc")
    , @NamedQuery(name = "Venta.findByCliente", query = "SELECT v FROM Venta v WHERE v.cliente = :cliente")
    , @NamedQuery(name = "Venta.findByRfc", query = "SELECT v FROM Venta v WHERE v.rfc = :rfc")
    , @NamedQuery(name = "Venta.findByDomicilio", query = "SELECT v FROM Venta v WHERE v.domicilio = :domicilio")
    , @NamedQuery(name = "Venta.findByNumExt", query = "SELECT v FROM Venta v WHERE v.numExt = :numExt")
    , @NamedQuery(name = "Venta.findByNumInt", query = "SELECT v FROM Venta v WHERE v.numInt = :numInt")
    , @NamedQuery(name = "Venta.findByCp", query = "SELECT v FROM Venta v WHERE v.cp = :cp")
    , @NamedQuery(name = "Venta.findByColonia", query = "SELECT v FROM Venta v WHERE v.colonia = :colonia")
    , @NamedQuery(name = "Venta.findByTelefono", query = "SELECT v FROM Venta v WHERE v.telefono = :telefono")
    , @NamedQuery(name = "Venta.findBySubTotal", query = "SELECT v FROM Venta v WHERE v.subTotal = :subTotal")
    , @NamedQuery(name = "Venta.findByIva", query = "SELECT v FROM Venta v WHERE v.iva = :iva")
    , @NamedQuery(name = "Venta.findByTotal", query = "SELECT v FROM Venta v WHERE v.total = :total")
    , @NamedQuery(name = "Venta.findByFechaElaboracion", query = "SELECT v FROM Venta v WHERE v.fechaElaboracion = :fechaElaboracion")
    , @NamedQuery(name = "Venta.findByFechaCreacion", query = "SELECT v FROM Venta v WHERE v.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Venta.findByFechaMod", query = "SELECT v FROM Venta v WHERE v.fechaMod = :fechaMod")})
public class Venta implements Serializable {

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
    @Column(name = "EstadoDoc")
    private String estadoDoc;
    @Basic(optional = false)
    @Column(name = "Cliente")
    private String cliente;
    @Basic(optional = false)
    @Column(name = "RFC")
    private String rfc;
    @Basic(optional = false)
    @Column(name = "Domicilio")
    private String domicilio;
    @Basic(optional = false)
    @Column(name = "NumExt")
    private String numExt;
    @Basic(optional = false)
    @Column(name = "NumInt")
    private String numInt;
    @Basic(optional = false)
    @Column(name = "CP")
    private String cp;
    @Basic(optional = false)
    @Column(name = "Colonia")
    private String colonia;
    @Basic(optional = false)
    @Column(name = "Telefono")
    private String telefono;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SubTotal")
    private BigDecimal subTotal;
    @Basic(optional = false)
    @Column(name = "IVA")
    private BigDecimal iva;
    @Basic(optional = false)
    @Column(name = "Total")
    private BigDecimal total;
    @Basic(optional = false)
    @Column(name = "FechaElaboracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaElaboracion;
    @Basic(optional = false)
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "FechaMod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDVenta")
    private Collection<VentaConcepto> ventaConceptoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDVenta")
    private Collection<VentaPago> ventaPagoCollection;

    public Venta() {
    }

    public Venta(Integer id) {
        this.id = id;
    }

    public Venta(Integer id, int activo, String estadoDoc, String cliente, String rfc, String domicilio, String numExt, String numInt, String cp, String colonia, String telefono, BigDecimal subTotal, BigDecimal iva, BigDecimal total, Date fechaElaboracion, Date fechaCreacion, Date fechaMod) {
        this.id = id;
        this.activo = activo;
        this.estadoDoc = estadoDoc;
        this.cliente = cliente;
        this.rfc = rfc;
        this.domicilio = domicilio;
        this.numExt = numExt;
        this.numInt = numInt;
        this.cp = cp;
        this.colonia = colonia;
        this.telefono = telefono;
        this.subTotal = subTotal;
        this.iva = iva;
        this.total = total;
        this.fechaElaboracion = fechaElaboracion;
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

    public String getEstadoDoc() {
        return estadoDoc;
    }

    public void setEstadoDoc(String estadoDoc) {
        this.estadoDoc = estadoDoc;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNumExt() {
        return numExt;
    }

    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    public String getNumInt() {
        return numInt;
    }

    public void setNumInt(String numInt) {
        this.numInt = numInt;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
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
    public Collection<VentaConcepto> getVentaConceptoCollection() {
        return ventaConceptoCollection;
    }

    public void setVentaConceptoCollection(Collection<VentaConcepto> ventaConceptoCollection) {
        this.ventaConceptoCollection = ventaConceptoCollection;
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
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataBase.Venta[ id=" + id + " ]";
    }
    
}
