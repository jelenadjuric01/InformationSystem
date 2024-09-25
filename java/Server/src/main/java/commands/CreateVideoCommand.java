/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class CreateVideoCommand extends Command {
    private final int userId;
    private final String name;
    private final int duration;

    public CreateVideoCommand(int userId, String name, int duration) {
        this.userId = userId;
        this.name = name;
        this.duration = duration;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S2;
    }

    @Override
    public Type getType() {
        return Type.CREATE_VIDEO;
    }
    
}
