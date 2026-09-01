package entities;

import entities.Consultation;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-09-01T12:08:06", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Medecin.class)
public class Medecin_ { 

    public static volatile SingularAttribute<Medecin, String> motDePasse;
    public static volatile SingularAttribute<Medecin, String> idMedecin;
    public static volatile SingularAttribute<Medecin, String> specialite;
    public static volatile SingularAttribute<Medecin, Date> dateExpirationCode;
    public static volatile SingularAttribute<Medecin, String> nomMedecin;
    public static volatile SingularAttribute<Medecin, String> emailMedecin;
    public static volatile SingularAttribute<Medecin, String> codeReset;
    public static volatile CollectionAttribute<Medecin, Consultation> consultationCollection;
    public static volatile SingularAttribute<Medecin, String> prenomMedecin;

}