/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem3;

import commands.Command;
import commands.DeleteRatingCommand;
import entities.Rating;
import entities.Video;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.FailedResponse;
import responses.JMSResponse;
import responses.StatusResponse;

/**
 *
 * @author Jelena
 */
public class DeleteRatingHandler extends CommandHandler {
    public DeleteRatingHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        DeleteRatingCommand data = (DeleteRatingCommand)cmd;
        Rating rating = em.find(Rating.class, data.getRatingId());
        if (rating == null) {
            return new FailedResponse(cmd, "Rating with specified ID not found.");
        }
        
        em.getTransaction().begin();
        em.remove(rating);
        em.getTransaction().commit();
        em.clear();
        return new StatusResponse(cmd);
    }
    
}
