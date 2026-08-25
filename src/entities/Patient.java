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
@Table(name = "patient")
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "Patient.findByIdPatient", query = "SELECT p FROM Patient p WHERE p.idPatient = :idPatient"),
    @NamedQuery(name = "Patient.findByNomPatient", query = "SELECT p FROM Patient p WHERE p.nomPatient = :nomPatient"),
    @NamedQuery(name = "Patient.findByPrenomPatient", query = "SELECT p FROM Patient p WHERE p.prenomPatient = :prenomPatient"),
    @NamedQuery(name = "Patient.findByAge", query = "SELECT p FROM Patient p WHERE p.age = :age"),
    @NamedQuery(name = "Patient.findByTelephone", query = "SELECT p FROM Patient p WHERE p.telephone = :telephone"),
    @NamedQuery(name = "Patient.findByEmailPatient", query = "SELECT p FROM Patient p WHERE p.emailPatient = :emailPatient")})
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdPatient")
    private String idPatient;
    @Basic(optional = false)
    @Column(name = "NomPatient")
    private String nomPatient;
    @Column(name = "PrenomPatient")
    private String prenomPatient;
    @Column(name = "Age")
    private Integer age;
    @Column(name = "Telephone")
    private String telephone;
    @Column(name = "EmailPatient")
    private String emailPatient;
    @OneToMany(mappedBy = "idPatient")
    private Collection<Paiement> paiementCollection;
    @OneToMany(mappedBy = "idPatient")
    private Collection<Consultation> consultationCollection;

    public Patient() {
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
    public Patient(String idPatient) {
        this.idPatient = idPatient;
    }

    public Patient(String idPatient, String nomPatient) {
        this.idPatient = idPatient;
        this.nomPatient = nomPatient;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public String getPrenomPatient() {
        return prenomPatient;
    }

    public void setPrenomPatient(String prenomPatient) {
        this.prenomPatient = prenomPatient;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmailPatient() {
        return emailPatient;
    }

    public void setEmailPatient(String emailPatient) {
        this.emailPatient = emailPatient;
    }

    public Collection<Paiement> getPaiementCollection() {
        return paiementCollection;
    }

    public void setPaiementCollection(Collection<Paiement> paiementCollection) {
        this.paiementCollection = paiementCollection;
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
        hash += (idPatient != null ? idPatient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.idPatient == null && other.idPatient != null) || (this.idPatient != null && !this.idPatient.equals(other.idPatient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Patient[ idPatient=" + idPatient + " ]";
    }
    
    @Column(name = "MotDePasse")
    private String motDePasse;
    @Column(name = "CodeReset")
    private String codeReset;
    @Column(name = "DateExpirationCode")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private java.util.Date dateExpirationCode;
}
