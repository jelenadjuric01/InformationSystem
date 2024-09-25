/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem2;

import commands.Command;
import commands.DeleteVideoCommand;
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
public class DeleteVideoHandler extends CommandHandler {
    public DeleteVideoHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        DeleteVideoCommand data = (DeleteVideoCommand)cmd;
        Video video = em.find(Video.class, data.getVideoId());
        if (video == null) {
            return new FailedResponse(cmd, "Video with specified ID not found.");
        }
        if(video.getIdU().getIdU()!=data.getUserId()){
            return new FailedResponse(cmd, "Video can only be deleted by its owner");

        }
        em.getTransaction().begin();
        em.remove(video);
        em.getTransaction().commit();
        em.clear();
        return new StatusResponse(cmd);
    }
}
