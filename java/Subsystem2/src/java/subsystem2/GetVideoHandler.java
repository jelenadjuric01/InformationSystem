/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem2;

import commands.Command;
import entities.Category;
import entities.Video;
import java.util.List;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
public class GetVideoHandler extends CommandHandler {
    public GetVideoHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        List<Video> results = em.createNamedQuery("Video.findAll", Video.class).getResultList();
        return new DataResponse<>(cmd, results);
    }
}
