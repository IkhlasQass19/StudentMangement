package entities;

import entities.Note;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-20T00:35:52", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Etudiant.class)
public class Etudiant_ { 

    public static volatile SingularAttribute<Etudiant, String> role;
    public static volatile SingularAttribute<Etudiant, Date> dateNaissance;
    public static volatile SingularAttribute<Etudiant, String> cin;
    public static volatile SingularAttribute<Etudiant, String> telephone;
    public static volatile SingularAttribute<Etudiant, Integer> sem;
    public static volatile SingularAttribute<Etudiant, Integer> id;
    public static volatile SingularAttribute<Etudiant, String> nom;
    public static volatile SingularAttribute<Etudiant, String> prenom;
    public static volatile CollectionAttribute<Etudiant, Note> noteCollection;
    public static volatile SingularAttribute<Etudiant, String> email;
    public static volatile SingularAttribute<Etudiant, Integer> age;

}