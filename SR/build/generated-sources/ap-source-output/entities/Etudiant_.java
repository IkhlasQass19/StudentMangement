package entities;

import entities.Note;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-14T23:50:32")
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