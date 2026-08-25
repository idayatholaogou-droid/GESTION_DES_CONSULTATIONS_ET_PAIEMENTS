package entities;

import entities.Consultation;
import entities.Facture;
import entities.Patient;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-08-25T19:12:36", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Paiement.class)
public class Paiement_ { 

    public static volatile SingularAttribute<Paiement, Date> datePaie;
    public static volatile SingularAttribute<Paiement, Double> montPaie;
    public static volatile CollectionAttribute<Paiement, Facture> factureCollection;
    public static volatile SingularAttribute<Paiement, Patient> idPatient;
    public static volatile SingularAttribute<Paiement, String> idPaie;
    public static volatile SingularAttribute<Paiement, String> modePaie;
    public static volatile SingularAttribute<Paiement, Consultation> idconsult;

}