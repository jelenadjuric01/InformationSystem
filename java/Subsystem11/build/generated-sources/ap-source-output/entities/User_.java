package entities;

import entities.Town;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-07-04T19:25:57")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> year;
    public static volatile SingularAttribute<User, Character> sex;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Town> idT;
    public static volatile SingularAttribute<User, Integer> idU;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> lastname;

}