/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class UpdateUserEmailCommand extends Command{
    private final String name;
    private final int userId;

    public UpdateUserEmailCommand(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S1;
    }

    @Override
    public Type getType() {
        return Type.UPDATE_USER_EMAIL;
    }
    
}
