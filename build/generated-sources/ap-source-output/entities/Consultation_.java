package entities;

import entities.Facture;
import entities.Medecin;
import entities.Paiement;
import entities.Patient;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-09-01T12:08:06", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, Medecin> idMedecin;
    public static volatile CollectionAttribute<Consultation, Facture> factureCollection;
    public static volatile SingularAttribute<Consultation, Date> dateconsult;
    public static volatile SingularAttribute<Consultation, Patient> idPatient;
    public static volatile SingularAttribute<Consultation, String> motif;
    public static volatile SingularAttribute<Consultation, String> diagnostic;
    public static volatile SingularAttribute<Consultation, Double> montconsult;
    public static volatile CollectionAttribute<Consultation, Paiement> paiementCollection;
    public static volatile SingularAttribute<Consultation, String> idconsult;

}