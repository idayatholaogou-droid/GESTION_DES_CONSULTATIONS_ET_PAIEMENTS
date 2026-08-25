/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Idayath
 */
@Entity
@Table(name = "paiement")
@NamedQueries({
    @NamedQuery(name = "Paiement.findAll", query = "SELECT p FROM Paiement p"),
    @NamedQuery(name = "Paiement.findByIdPaie", query = "SELECT p FROM Paiement p WHERE p.idPaie = :idPaie"),
    @NamedQuery(name = "Paiement.findByMontPaie", query = "SELECT p FROM Paiement p WHERE p.montPaie = :montPaie"),
    @NamedQuery(name = "Paiement.findByDatePaie", query = "SELECT p FROM Paiement p WHERE p.datePaie = :datePaie"),
    @NamedQuery(name = "Paiement.findByModePaie", query = "SELECT p FROM Paiement p WHERE p.modePaie = :modePaie")})
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdPaie")
    private String idPaie;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MontPaie")
    private Double montPaie;
    @Column(name = "DatePaie")
    @Temporal(TemporalType.DATE)
    private Date datePaie;
    @Column(name = "ModePaie")
    private String modePaie;
    public String getIdconsult() {
    return idconsult != null ? idconsult.getIdconsult() : null;
}

public String getIdPatient() {
    return idPatient != null ? idPatient.getIdPatient() : null;
}

public void setIdconsult(String idconsult) {
    if (this.idconsult == null) this.idconsult = new Consultation();
    this.idconsult.setIdconsult(idconsult);
}

public void setIdPatient(String idPatient) {
    if (this.idPatient == null) this.idPatient = new Patient();
    this.idPatient.setIdPatient(idPatient);
}
@JoinColumn(name = "idconsult", referencedColumnName = "idconsult")
@ManyToOne
    private Consultation idconsult;
    @JoinColumn(name = "IdPatient", referencedColumnName = "IdPatient")
    @ManyToOne
    private Patient idPatient;
    @OneToMany(mappedBy = "idPaie")
    private Collection<Facture> factureCollection;

    public Paiement() {
    }

    public Paiement(String idPaie) {
        this.idPaie = idPaie;
    }

    public String getIdPaie() {
        return idPaie;
    }

    public void setIdPaie(String idPaie) {
        this.idPaie = idPaie;
    }

    public Double getMontPaie() {
        return montPaie;
    }

    public void setMontPaie(Double montPaie) {
        this.montPaie = montPaie;
    }

    public Date getDatePaie() {
        return datePaie;
    }

    public void setDatePaie(Date datePaie) {
        this.datePaie = datePaie;
    }

    public String getModePaie() {
        return modePaie;
    }

    public void setModePaie(String modePaie) {
        this.modePaie = modePaie;
    }

    public Consultation getidconsult() {
        return idconsult;
    }

    public void setIdconsult(Consultation idconsult) {
        this.idconsult = idconsult;
    }

    public Patient getidPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public Collection<Facture> getFactureCollection() {
        return factureCollection;
    }

    public void setFactureCollection(Collection<Facture> factureCollection) {
        this.factureCollection = factureCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaie != null ? idPaie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paiement)) {
            return false;
        }
        Paiement other = (Paiement) object;
        if ((this.idPaie == null && other.idPaie != null) || (this.idPaie != null && !this.idPaie.equals(other.idPaie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paiement[ idPaie=" + idPaie + " ]";
    }
    
}
