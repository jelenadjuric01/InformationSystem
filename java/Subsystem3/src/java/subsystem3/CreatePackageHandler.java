/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem3;

import commands.Command;
import commands.CreatePackageCommand;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.JMSResponse;
import entities.Package;
import responses.FailedResponse;
/**
 *
 * @author Jelena
 */
public class CreatePackageHandler extends CommandHandler{
    public CreatePackageHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        CreatePackageCommand data = (CreatePackageCommand)cmd;
        if(data.getPrice()<=0){
            return new FailedResponse(cmd, "Price has to be bigger than 0");
        }
         Package pack = new Package();
        pack.setPrice(data.getPrice());
        em.getTransaction().begin();
        em.persist(pack);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, pack);
    }
}
