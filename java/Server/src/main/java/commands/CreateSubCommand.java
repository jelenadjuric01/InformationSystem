/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class CreateSubCommand extends Command {
    private final int packageId;
    private final int userId;

    public CreateSubCommand( int packageId, int userId) {
        this.packageId = packageId;
        this.userId = userId;
    }

   

    public int getPackageId() {
        return packageId;
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
        return Type.CREATE_SUB;
    }
    
}
