/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem11;

import commands.Command;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
public class GetUsersHandler extends CommandHandler {
    public GetUsersHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        List<User> results = em.createNamedQuery("User.findAll", User.class).getResultList();
        return new DataResponse<>(cmd, results);
    }
    
}
