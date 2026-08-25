/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Idayath
 */
@Entity
@Table(name = "facture")
@NamedQueries({
    @NamedQuery(name = "Facture.findAll", query = "SELECT f FROM Facture f"),
    @NamedQuery(name = "Facture.findByIdFacture", query = "SELECT f FROM Facture f WHERE f.idFacture = :idFacture"),
    @NamedQuery(name = "Facture.findByDateFacture", query = "SELECT f FROM Facture f WHERE f.dateFacture = :dateFacture"),
    @NamedQuery(name = "Facture.findByStatut", query = "SELECT f FROM Facture f WHERE f.statut = :statut"),
    @NamedQuery(name = "Facture.findByMontFacture", query = "SELECT f FROM Facture f WHERE f.montFacture = :montFacture")})
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdFacture")
    private String idFacture;
    @Column(name = "DateFacture")
    @Temporal(TemporalType.DATE)
    private Date dateFacture;
    @Column(name = "Statut")
    private String statut;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MontFacture")
    private Double montFacture;
    // Getters
public String getIdconsult() {
    return idconsult != null ? idconsult.getIdconsult() : null;
}

public String getIdPaie() {
    return idPaie != null ? idPaie.getIdPaie() : null;
}

// Setters
public void setIdconsult(String idconsult) {
    if (this.idconsult == null) this.idconsult = new Consultation();
    this.idconsult.setIdconsult(idconsult);
}

public void setIdPaie(String idPaie) {
    if (this.idPaie == null) this.idPaie = new Paiement();
    this.idPaie.setIdPaie(idPaie);
}
    @JoinColumn(name = "idconsult", referencedColumnName = "idconsult")
    @ManyToOne
    private Consultation idconsult;
    @JoinColumn(name = "IdPaie", referencedColumnName = "IdPaie")
    @ManyToOne
    private Paiement idPaie;

    public Facture() {
    }

    public Facture(String idFacture) {
        this.idFacture = idFacture;
    }

    public String getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Double getMontFacture() {
        return montFacture;
    }

    public void setMontFacture(Double montFacture) {
        this.montFacture = montFacture;
    }

    public Consultation getidconsult() {
        return idconsult;
    }

    public void setIdconsult(Consultation idconsult) {
        this.idconsult = idconsult;
    }

    public Paiement getidPaie() {
        return idPaie;
    }

    public void setIdPaie(Paiement idPaie) {
        this.idPaie = idPaie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacture != null ? idFacture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.idFacture == null && other.idFacture != null) || (this.idFacture != null && !this.idFacture.equals(other.idFacture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Facture[ idFacture=" + idFacture + " ]";
    }

    public Object getIdConsult() {
        return idconsult != null ? idconsult.getIdconsult() : null;
    }

    public void setIdConsult(String idconsult) {
       if (this.idconsult == null) this.idconsult = new Consultation();
    this.idconsult.setIdconsult(idconsult);
    }
    
}
