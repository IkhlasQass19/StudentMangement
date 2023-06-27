package entities;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-20T00:35:52", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Coordinateur.class)
public class Coordinateur_ { 

    public static volatile SingularAttribute<Coordinateur, String> role;
    public static volatile SingularAttribute<Coordinateur, Date> dateNaissance;
    public static volatile SingularAttribute<Coordinateur, String> cin;
    public static volatile SingularAttribute<Coordinateur, String> telephone;
    public static volatile SingularAttribute<Coordinateur, Integer> id;
    public static volatile SingularAttribute<Coordinateur, String> nom;
    public static volatile SingularAttribute<Coordinateur, String> prenom;
    public static volatile SingularAttribute<Coordinateur, String> email;
    public static volatile SingularAttribute<Coordinateur, Integer> age;

}