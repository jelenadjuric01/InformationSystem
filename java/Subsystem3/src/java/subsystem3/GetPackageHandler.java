/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem3;

import commands.Command;
import java.util.List;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
public class GetPackageHandler extends CommandHandler {
    public GetPackageHandler(EntityManager em) {
        super(em);
    }
    
    
    @Override
    public JMSResponse handle(Command cmd) {
        List<entities.Package> results = em.createNamedQuery("Package.findAll", entities.Package.class).getResultList();
        return new DataResponse<>(cmd, results);
    }
}
