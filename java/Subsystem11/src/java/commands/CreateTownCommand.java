/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class CreateTownCommand extends Command {
    private final String name;

    public CreateTownCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S1;
    }

    @Override
    public Type getType() {
        return Type.CREATE_TOWN;
    }
    
}
