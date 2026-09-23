package entities;

import java.sql.Date;
import java.sql.Timestamp;

public class RendezVous {

    private String idRendezVous;
    private String idPatient;
    private Date dateSouhaitee;
    private String motif;
    private String statut; // "En attente", "Confirmé", "Refusé"
    private Timestamp dateConfirmee;
    private String idMedecin;
    private Timestamp dateCreation;

    public RendezVous() {
    }

    public String getIdRendezVous() {
        return idRendezVous;
    }

    public void setIdRendezVous(String idRendezVous) {
        this.idRendezVous = idRendezVous;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public Date getDateSouhaitee() {
        return dateSouhaitee;
    }

    public void setDateSouhaitee(Date dateSouhaitee) {
        this.dateSouhaitee = dateSouhaitee;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Timestamp getDateConfirmee() {
        return dateConfirmee;
    }

    public void setDateConfirmee(Timestamp dateConfirmee) {
        this.dateConfirmee = dateConfirmee;
    }

    public String getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(String idMedecin) {
        this.idMedecin = idMedecin;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }
}