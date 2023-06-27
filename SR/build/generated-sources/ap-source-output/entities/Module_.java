package entities;

import entities.Note;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-14T23:50:32")
@StaticMetamodel(Module.class)
public class Module_ { 

    public static volatile SingularAttribute<Module, String> departementDAttache;
    public static volatile SingularAttribute<Module, String> libelleDuModule;
    public static volatile SingularAttribute<Module, String> etablissement;
    public static volatile SingularAttribute<Module, Integer> id;
    public static volatile SingularAttribute<Module, String> intervenant;
    public static volatile CollectionAttribute<Module, Note> noteCollection;

}