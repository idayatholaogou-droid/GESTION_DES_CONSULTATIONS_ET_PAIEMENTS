package entities;

import entities.Consultation;
import entities.Paiement;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-08-25T19:12:36", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Facture.class)
public class Facture_ { 

    public static volatile SingularAttribute<Facture, Date> dateFacture;
    public static volatile SingularAttribute<Facture, Double> montFacture;
    public static volatile SingularAttribute<Facture, String> idFacture;
    public static volatile SingularAttribute<Facture, Paiement> idPaie;
    public static volatile SingularAttribute<Facture, String> statut;
    public static volatile SingularAttribute<Facture, Consultation> idconsult;

}