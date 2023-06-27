package entities;

import entities.Note;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-14T23:50:32")
@StaticMetamodel(Enseignant.class)
public class Enseignant_ { 

    public static volatile SingularAttribute<Enseignant, String> role;
    public static volatile SingularAttribute<Enseignant, Date> dateNaissance;
    public static volatile SingularAttribute<Enseignant, String> cin;
    public static volatile SingularAttribute<Enseignant, String> telephone;
    public static volatile SingularAttribute<Enseignant, Integer> id;
    public static volatile SingularAttribute<Enseignant, String> nom;
    public static volatile SingularAttribute<Enseignant, String> prenom;
    public static volatile CollectionAttribute<Enseignant, Note> noteCollection;
    public static volatile SingularAttribute<Enseignant, String> email;
    public static volatile SingularAttribute<Enseignant, Integer> age;

}