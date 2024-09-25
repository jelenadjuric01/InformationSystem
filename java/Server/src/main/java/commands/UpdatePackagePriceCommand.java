/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class UpdatePackagePriceCommand extends Command {
    private final int packageId;
    private final int price;

    public UpdatePackagePriceCommand(int packageId, int price) {
        this.packageId = packageId;
        this.price = price;
    }

    public int getPackageId() {
        return packageId;
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
        return Type.UPDATE_PACKAGE_PRICE;
    }
    
}
