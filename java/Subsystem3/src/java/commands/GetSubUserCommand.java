/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class GetSubUserCommand extends Command {
    private final int userId;

    public GetSubUserCommand(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S3;
    }

    @Override
    public Type getType() {
        return Type.GET_SUBS_USER;
    }
    
}
