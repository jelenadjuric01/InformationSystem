/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem3;

import commands.Command;
import commands.CreateRatingCommand;
import commands.CreateWatchedCommand;
import entities.Rating;
import entities.User;
import entities.Video;
import entities.Watched;
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
public class CreateRatingHandler extends CommandHandler{
    public CreateRatingHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        CreateRatingCommand data = (CreateRatingCommand)cmd;
        User user = em.find(User.class, data.getUserId());
        if(user==null){
            return new FailedResponse(cmd, "User with given ID doesnt exist");
        }
        Video video = em.find(Video.class, data.getVideoId());
        if(video==null){
            return new FailedResponse(cmd, "Video with given ID doesnt exist");
        }
        if(data.getRate()<1 || data.getRate()>5){
            return new FailedResponse(cmd, "Rate needs to be in range from 1 to 5");
        }
        Rating singleResult = em.createQuery("select r from Rating r where r.idU.idU=:idU and r.idV.idV=:idV", Rating.class).setParameter("idU", user.getIdU()).setParameter("idV", video.getIdV()).getSingleResult();
        if(singleResult!=null){
            return new FailedResponse(cmd, "User with given ID has already left a rate for the video with given ID");
        }
        Rating rating = new Rating();
        rating.setIdV(video);
        rating.setIdU(user);
        rating.setRate(data.getRate());
        rating.setDateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        em.getTransaction().begin();
        em.persist(rating);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, rating);
    }
    
}
