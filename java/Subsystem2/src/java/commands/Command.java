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
        CREATE_CATEGORY,
        CREATE_VIDEO,
        UPDATE_VIDEO_NAME,
        ADD_CATEGORY_VIDEO,
        DELETE_VIDEO,
        GET_CATEGORIES,
        GET_VIDEOS,
        GET_CATEGORY_VIDEO
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
