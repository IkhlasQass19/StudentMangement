package entities;

import entities.Note;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-20T00:35:52", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Module.class)
public class Module_ { 

    public static volatile SingularAttribute<Module, String> libelleDuModule;
    public static volatile SingularAttribute<Module, String> idprof;
    public static volatile SingularAttribute<Module, String> etablissement;
    public static volatile SingularAttribute<Module, Integer> id;
    public static volatile SingularAttribute<Module, String> intervenant;
    public static volatile CollectionAttribute<Module, Note> noteCollection;

}