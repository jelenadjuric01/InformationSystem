/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem11;

import commands.Command;
import commands.CreateTownCommand;
import entities.Town;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
public class CreateTownHandler extends CommandHandler{
    public CreateTownHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        CreateTownCommand data = (CreateTownCommand)cmd;
        Town place = new Town();
        place.setName(data.getName());
        em.getTransaction().begin();
        em.persist(place);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, place);
    }
}
