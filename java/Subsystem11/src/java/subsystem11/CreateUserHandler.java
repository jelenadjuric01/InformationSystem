/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem11;

import commands.Command;
import commands.CreateUserCommand;
import entities.Town;
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
public class CreateUserHandler extends CommandHandler {
    
 public CreateUserHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        CreateUserCommand data = (CreateUserCommand)cmd;
        Town place = em.find(Town.class, data.getTownId());
        if (place == null) {
            return new FailedResponse(cmd, "No place with given ID exists.");
        }
        if(!data.getSex().equals('M') && !data.getSex().equals('F')){
            return new FailedResponse(cmd, "Sex can only be M or F");
       
        }
        if(data.getYear()<1930){
            return new FailedResponse(cmd, "Year of birth has to be bigger than 1930");
        }
        List<User> userEmail = em.createNamedQuery("User.findByEmail",User.class).setParameter("email", data.getEmail()).getResultList();
        if(!userEmail.isEmpty()){
             return new FailedResponse(cmd, "Email already exists");
           
        }
        User user = new User();
        user.setEmail(data.getEmail());
        user.setName(data.getName());
        user.setIdT(place);
        user.setLastname(data.getLastname());
        user.setSex(data.getSex());
        user.setYear(data.getYear());
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, user);
    }
}
