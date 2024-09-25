/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem3;

import commands.Command;
import commands.UpdatePackagePriceCommand;
import entities.*;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.FailedResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
public class UpdatePackagePriceHandler extends CommandHandler {
    public UpdatePackagePriceHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        UpdatePackagePriceCommand data = (UpdatePackagePriceCommand)cmd;
        entities.Package pack = em.find(entities.Package.class, data.getPackageId());
        if (pack == null) {
            return new FailedResponse(cmd, "No package with given ID exists.");
        }
        if(data.getPrice()<=0){
            return new FailedResponse(cmd, "Package price has to be bigger than 0");
        }
        pack.setPrice(data.getPrice());
        em.getTransaction().begin();
        em.persist(pack);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, pack);
    }
    
}
