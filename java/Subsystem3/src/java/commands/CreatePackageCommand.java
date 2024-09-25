/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class CreatePackageCommand extends Command {
    private final int price;

    public CreatePackageCommand(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S3;
    }

    @Override
    public Type getType() {
        return Type.CREATE_PACKAGE;
    }
    
}
