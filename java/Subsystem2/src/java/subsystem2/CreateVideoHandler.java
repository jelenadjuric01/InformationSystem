/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem2;

import commands.Command;
import commands.CreateCategoryCommand;
import commands.CreateVideoCommand;
import entities.Category;
import entities.User;
import entities.Video;
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
public class CreateVideoHandler extends CommandHandler{
    public CreateVideoHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        CreateVideoCommand data = (CreateVideoCommand)cmd;
        if(data.getDuration()<=0){
            return new FailedResponse(cmd, "Video has to have duration that is bigger than 0");

        }
         Video video = new Video();
         User user = em.find(User.class, data.getUserId());
         if(user==null){
             return new FailedResponse(cmd, "Owner of the video doesnt exist");
         }
         video.setIdU(user);
        video.setName(data.getName());
        video.setDateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        video.setDuration(data.getDuration());
        em.getTransaction().begin();
        em.persist(video);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, video);
    }
}