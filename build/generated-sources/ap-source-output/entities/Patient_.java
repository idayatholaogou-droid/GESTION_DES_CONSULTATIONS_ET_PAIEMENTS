package entities;

import entities.Consultation;
import entities.Paiement;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-08-25T19:12:36", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Patient.class)
public class Patient_ { 

    public static volatile CollectionAttribute<Patient, Consultation> consultationCollection;
    public static volatile SingularAttribute<Patient, String> idPatient;
    public static volatile SingularAttribute<Patient, String> telephone;
    public static volatile CollectionAttribute<Patient, Paiement> paiementCollection;
    public static volatile SingularAttribute<Patient, String> prenomPatient;
    public static volatile SingularAttribute<Patient, String> nomPatient;
    public static volatile SingularAttribute<Patient, Integer> age;
    public static volatile SingularAttribute<Patient, String> emailPatient;

}