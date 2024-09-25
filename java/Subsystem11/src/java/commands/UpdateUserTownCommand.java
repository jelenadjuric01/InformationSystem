/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class UpdateUserTownCommand extends Command{
    private final int townId;
    private final int userId;

    public UpdateUserTownCommand(int townId, int userId) {
        this.townId = townId;
        this.userId = userId;
    }

    public int getTownId() {
        return townId;
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
        return Type.UPDATE_USER_TOWN;
    }
    
}
