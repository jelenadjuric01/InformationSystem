/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem3;

import commands.Command;
import commands.UpdatePackagePriceCommand;
import commands.UpdateRatingRateCommand;
import entities.Rating;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.FailedResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
public class UpdateRatingHandler extends CommandHandler {
    public UpdateRatingHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        UpdateRatingRateCommand data = (UpdateRatingRateCommand)cmd;
        Rating rating = em.find(Rating.class, data.getRatingdId());
        if (rating == null) {
            return new FailedResponse(cmd, "No rating with given ID exists.");
        }
        if(data.getRate()<1 || data.getRate()>5){
            return new FailedResponse(cmd, "Rate can only be in range from 1 to 5");
        }
        rating.setRate(data.getRate());
        rating.setDateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        em.getTransaction().begin();
        em.persist(rating);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, rating);
    }
    
}
