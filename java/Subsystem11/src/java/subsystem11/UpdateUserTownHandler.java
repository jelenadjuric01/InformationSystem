/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem11;

import commands.Command;
import commands.UpdateUserTownCommand;
import entities.Town;
import entities.User;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.FailedResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
public class UpdateUserTownHandler extends CommandHandler {
    public UpdateUserTownHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        UpdateUserTownCommand data = (UpdateUserTownCommand)cmd;
        User user = em.find(User.class, data.getUserId());
        if (user == null) {
            return new FailedResponse(cmd, "No user with given ID exists.");
        }
        Town place = em.find(Town.class, data.getTownId());
        if (place == null) {
            return new FailedResponse(cmd, "No town with given ID exists.");
        }
        user.setIdT(place);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, user);
    }
}