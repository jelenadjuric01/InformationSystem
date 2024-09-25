/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem11;

import commands.Command;
import commands.UpdateUserEmailCommand;
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
public class UpdateUserEmailHandler extends CommandHandler {
    public UpdateUserEmailHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        UpdateUserEmailCommand data = (UpdateUserEmailCommand)cmd;
        User client = em.find(User.class, data.getUserId());
        if (client == null) {
            return new FailedResponse(cmd, "No user with given ID exists.");
        }
         List<User> userEmail = em.createNamedQuery("User.findByEmail",User.class).setParameter("email", data.getName()).getResultList();
        if(!userEmail.isEmpty()){
             return new FailedResponse(cmd, "Email already exists");
           
        }
        client.setEmail(data.getName());
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, client);
    }
}
