/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Jelena
 */
public abstract class Command implements Serializable{
    public enum Type{
        
        CREATE_PACKAGE,
        UPDATE_PACKAGE_PRICE,
        CREATE_SUB,
        CREATE_WATCHED,
        CREATE_RATING,
        UPDATE_RATING_VIDEO,
        DELETE_RATING,
        GET_PACKAGES,
        GET_SUBS_USER,
        GET_WATCHES_VIDEO,
        GET_RATINGS_VIDEO
    };
    
    public enum Endpoint{
        S1,S2,S3
    };
    protected final UUID uuid;
    public Command(){
        uuid=UUID.randomUUID();
    }
    
    public String getId(){
        return uuid.toString();
    }
    
    abstract public Endpoint getEndpoint();
    abstract public Type getType();
    @Override
    public String toString() {
        return "Command{type=" +getType()+",endpoint="+getEndpoint()+ ",uuid=" + uuid + '}';
    }
    
    
}
