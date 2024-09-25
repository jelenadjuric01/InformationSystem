/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class GetPackageCommand extends Command {

    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S3;
    }

    @Override
    public Type getType() {
        return Type.GET_PACKAGES;
    }
    
}
