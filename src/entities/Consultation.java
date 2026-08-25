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
@Table(name = "consultation")
@NamedQueries({
    @NamedQuery(name = "Consultation.findAll", query = "SELECT c FROM Consultation c"),
    @NamedQuery(name = "Consultation.findByIdconsult", query = "SELECT c FROM Consultation c WHERE c.idconsult = :idconsult"),
    @NamedQuery(name = "Consultation.findByDateconsult", query = "SELECT c FROM Consultation c WHERE c.dateconsult = :dateconsult"),
    @NamedQuery(name = "Consultation.findByMotif", query = "SELECT c FROM Consultation c WHERE c.motif = :motif"),
    @NamedQuery(name = "Consultation.findByDiagnostic", query = "SELECT c FROM Consultation c WHERE c.diagnostic = :diagnostic"),
    @NamedQuery(name = "Consultation.findByMontconsult", query = "SELECT c FROM Consultation c WHERE c.montconsult = :montconsult")})
public class Consultation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idconsult")
    private String idconsult;
    @Basic(optional = false)
    @Column(name = "dateconsult")
    @Temporal(TemporalType.DATE)
    private Date dateconsult;
    @Basic(optional = false)
    @Column(name = "motif")
    private String motif;
    @Basic(optional = false)
    @Column(name = "diagnostic")
    private String diagnostic;
    @Basic(optional = false)
    @Column(name = "montconsult")
    private double montconsult;
    @OneToMany(mappedBy = "idconsult")
    private Collection<Paiement> paiementCollection;
    @OneToMany(mappedBy = "idconsult")
    private Collection<Facture> factureCollection;
    public String getIdPatient() {
    return idPatient != null ? idPatient.getIdPatient() : null;
}

public String getIdMedecin() {
    return idMedecin != null ? idMedecin.getIdMedecin() : null;
}

public void setIdPatient(String idPatient) {
    if (this.idPatient == null) this.idPatient = new Patient();
    this.idPatient.setIdPatient(idPatient);
}

public void setIdMedecin(String idMedecin) {
    if (this.idMedecin == null) this.idMedecin = new Medecin();
    this.idMedecin.setIdMedecin(idMedecin);
}
    @JoinColumn(name = "IdMedecin", referencedColumnName = "IdMedecin")
    @ManyToOne
    private Medecin idMedecin;
    @JoinColumn(name = "IdPatient", referencedColumnName = "IdPatient")
    @ManyToOne
    private Patient idPatient;
    
  

    public Consultation() {
    }

    public Consultation(String idconsult) {
        this.idconsult = idconsult;
    }

    public Consultation(String idconsult, Date dateconsult, String motif, String diagnostic, double montconsult) {
        this.idconsult = idconsult;
        this.dateconsult = dateconsult;
        this.motif = motif;
        this.diagnostic = diagnostic;
        this.montconsult = montconsult;
        
    }

    public String getIdconsult() {
        return idconsult;
    }

    public void setIdconsult(String idconsult) {
        this.idconsult = idconsult;
    }

    public Date getDateconsult() {
        return dateconsult;
    }

    public void setDateconsult(Date dateconsult) {
        this.dateconsult = dateconsult;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public double getMontconsult() {
        return montconsult;
    }

    public void setMontconsult(double montconsult) {
        this.montconsult = montconsult;
    }

    public Collection<Paiement> getPaiementCollection() {
        return paiementCollection;
    }

    public void setPaiementCollection(Collection<Paiement> paiementCollection) {
        this.paiementCollection = paiementCollection;
    }

    public Collection<Facture> getFactureCollection() {
        return factureCollection;
    }

    public void setFactureCollection(Collection<Facture> factureCollection) {
        this.factureCollection = factureCollection;
    }

    public Medecin getidMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Medecin idMedecin) {
        this.idMedecin = idMedecin;
    }

    public Patient getidPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconsult != null ? idconsult.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultation)) {
            return false;
        }
        Consultation other = (Consultation) object;
        if ((this.idconsult == null && other.idconsult != null) || (this.idconsult != null && !this.idconsult.equals(other.idconsult))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Consultation[ idconsult=" + idconsult + " ]";
    }
    
}
