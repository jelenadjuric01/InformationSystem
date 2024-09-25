/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem3;

import commands.Command;
import commands.CreateSubCommand;
import commands.CreateWatchedCommand;
import entities.Subscription;
import entities.User;
import entities.Video;
import entities.Watched;
import java.sql.Timestamp;
import java.util.Calendar;
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
public class CreateWatchedHandler extends CommandHandler{
    public CreateWatchedHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        CreateWatchedCommand data = (CreateWatchedCommand)cmd;
        User user = em.find(User.class, data.getUserId());
        if(user==null){
            return new FailedResponse(cmd, "User with given ID doesnt exist");
        }
        Video video = em.find(Video.class, data.getVideoId());
        if(video==null){
            return new FailedResponse(cmd, "Video with given ID doesnt exist");
        }
        if(data.getSeconds()<=0){
            return new FailedResponse(cmd, "User needs to watch video for more than 0 seconds");
        }
        if(data.getSeconds()+data.getStart()>video.getDuration()){
            return new FailedResponse(cmd, "Make sure that the video is being watched within its duration");
        }
        if(data.getStart()<0 || data.getStart()>=video.getDuration()){
            return new FailedResponse(cmd, "Video can start from second 0 to "+(video.getDuration()-1));
        }
        Watched watched = new Watched();
        watched.setIdV(video);
        watched.setIdU(user);
        watched.setSecondsWatched(data.getSeconds());
        watched.setStartSecond(data.getStart());
        watched.setDateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        em.getTransaction().begin();
        em.persist(watched);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, watched);
    }
    
}
