/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem3;

import commands.Command;
import commands.GetSubUserCommand;
import entities.Subscription;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.FailedResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
public class GetSubUserHandler extends CommandHandler {
    public GetSubUserHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        GetSubUserCommand data = (GetSubUserCommand)cmd;
        User user = em.find(User.class, data.getUserId());
        if(user==null){
            return new FailedResponse(cmd, "User with given ID doesnt exist");
        }
        List<Subscription> results = em.createQuery("SELECT b FROM Subscription b WHERE b.idU.idU = :idU", Subscription.class).setParameter("idU", data.getUserId()).getResultList();
        return new DataResponse<>(cmd, results);
    }
}
