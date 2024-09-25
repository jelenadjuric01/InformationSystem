/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import commands.Command;

/**
 *
 * @author Jelena
 */
public class GetTownCommand extends Command {
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S1;
    }

    @Override
    public Type getType() {
        return Type.GET_TOWNS;
    }
    
}
