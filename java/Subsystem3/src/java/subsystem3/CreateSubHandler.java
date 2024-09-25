/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem3;

import commands.Command;
import commands.CreatePackageCommand;
import commands.CreateSubCommand;
import entities.User;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.FailedResponse;
import responses.JMSResponse;
import entities.Package;
import entities.Subscription;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
/**
 *
 * @author Jelena
 */
public class CreateSubHandler extends CommandHandler{
    public CreateSubHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        CreateSubCommand data = (CreateSubCommand)cmd;
        User user = em.find(User.class, data.getUserId());
        if(user==null){
            return new FailedResponse(cmd, "User with given ID doesnt exist");
        }
        Package pack = em.find(Package.class, data.getPackageId());
        if(pack==null){
            return new FailedResponse(cmd, "Package with given ID doesnt exist");
        }
        List<Subscription> sub = em.createQuery("select s from Subscription s where s.idU.idU=:idU and S.idP.idP=:idP ORDER BY s.dateTime DESC",Subscription.class ).setParameter("idU", data.getUserId()).setParameter("idP", data.getPackageId()).getResultList();
        if(!sub.isEmpty()){
            Subscription current = sub.get(0);
            Calendar curr = Calendar.getInstance();
            curr.setTime(current.getDateTime());
            Calendar today = Calendar.getInstance();
            today.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
            if(today.get(Calendar.YEAR)-curr.get(Calendar.YEAR)==0 && today.get(Calendar.MONTH)-curr.get(Calendar.MONTH)<=1 && today.get(Calendar.DAY_OF_MONTH)-curr.get(Calendar.DAY_OF_MONTH)<=1){
            return new FailedResponse(cmd, "User with given ID already has a subscription for that package that hasnt expired");
        }
                    
            
        }
        Subscription subs = new Subscription();
        subs.setIdP(pack);
        subs.setIdU(user);
        subs.setPrice(pack.getPrice());
        subs.setDateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        em.getTransaction().begin();
        em.persist(subs);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, subs);
    }
    
}
