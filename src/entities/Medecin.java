/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import javax.persistence.Temporal;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Idayath
 */
@Entity
@Table(name = "medecin")
@NamedQueries({
    @NamedQuery(name = "Medecin.findAll", query = "SELECT m FROM Medecin m"),
    @NamedQuery(name = "Medecin.findByIdMedecin", query = "SELECT m FROM Medecin m WHERE m.idMedecin = :idMedecin"),
    @NamedQuery(name = "Medecin.findByNomMedecin", query = "SELECT m FROM Medecin m WHERE m.nomMedecin = :nomMedecin"),
    @NamedQuery(name = "Medecin.findByPrenomMedecin", query = "SELECT m FROM Medecin m WHERE m.prenomMedecin = :prenomMedecin"),
    @NamedQuery(name = "Medecin.findBySpecialite", query = "SELECT m FROM Medecin m WHERE m.specialite = :specialite"),
    @NamedQuery(name = "Medecin.findByEmailMedecin", query = "SELECT m FROM Medecin m WHERE m.emailMedecin = :emailMedecin")})
public class Medecin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdMedecin")
    private String idMedecin;
    @Basic(optional = false)
    @Column(name = "NomMedecin")
    private String nomMedecin;
    @Column(name = "PrenomMedecin")
    private String prenomMedecin;
    @Column(name = "Specialite")
    private String specialite;
    @Column(name = "EmailMedecin")
    private String emailMedecin;
    @OneToMany(mappedBy = "idMedecin")
    private Collection<Consultation> consultationCollection;

    public Medecin() {
    }
    public String getMotDePasse() {
        return motDePasse;
    }
     
    public void setMotDePasse(String motDePasse) {
       this.motDePasse = motDePasse;
    }

    public String getCodeReset() {
        return codeReset;
    }

    public void setCodeReset(String codeReset) {
      this.codeReset = codeReset;
    }

    public java.util.Date getDateExpirationCode() {
        return dateExpirationCode;
    }

    public void setDateExpirationCode(java.util.Date dateExpirationCode) {
       this.dateExpirationCode = dateExpirationCode;
    }
    public Medecin(String idMedecin) {
        this.idMedecin = idMedecin;
    }

    public Medecin(String idMedecin, String nomMedecin) {
        this.idMedecin = idMedecin;
        this.nomMedecin = nomMedecin;
    }

    public String getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(String idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getNomMedecin() {
        return nomMedecin;
    }

    public void setNomMedecin(String nomMedecin) {
        this.nomMedecin = nomMedecin;
    }

    public String getPrenomMedecin() {
        return prenomMedecin;
    }

    public void setPrenomMedecin(String prenomMedecin) {
        this.prenomMedecin = prenomMedecin;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getEmailMedecin() {
        return emailMedecin;
    }

    public void setEmailMedecin(String emailMedecin) {
        this.emailMedecin = emailMedecin;
    }

    public Collection<Consultation> getConsultationCollection() {
        return consultationCollection;
    }

    public void setConsultationCollection(Collection<Consultation> consultationCollection) {
        this.consultationCollection = consultationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedecin != null ? idMedecin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medecin)) {
            return false;
        }
        Medecin other = (Medecin) object;
        if ((this.idMedecin == null && other.idMedecin != null) || (this.idMedecin != null && !this.idMedecin.equals(other.idMedecin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Medecin[ idMedecin=" + idMedecin + " ]";
    }
    @Column(name = "MotDePasse")
    private String motDePasse;
    @Column(name = "CodeReset")
    private String codeReset;
    @Column(name = "DateExpirationCode")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private java.util.Date dateExpirationCode;
}
