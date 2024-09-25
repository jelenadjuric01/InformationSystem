/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem2;

import commands.Command;
import commands.UpdateVideoNameCommand;
import entities.User;
import entities.Video;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.FailedResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
public class UpdateVideoNameHandler extends CommandHandler {
    public UpdateVideoNameHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        UpdateVideoNameCommand data = (UpdateVideoNameCommand)cmd;
        Video video = em.find(Video.class, data.getVideoId());
        if (video == null) {
            return new FailedResponse(cmd, "No video with given ID exists.");
        }
        video.setName(data.getName());
        em.getTransaction().begin();
        em.persist(video);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, video);
    }
    
}
