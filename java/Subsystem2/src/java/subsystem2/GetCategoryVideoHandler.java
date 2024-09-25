/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem2;

import commands.Command;
import commands.GetVideoCategoryCommand;
import entities.Belongs;
import entities.Category;
import entities.Video;
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
public class GetCategoryVideoHandler extends CommandHandler {
    public GetCategoryVideoHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        GetVideoCategoryCommand data = (GetVideoCategoryCommand)cmd;
        Video video = em.find(Video.class, data.getVideoId());
        if(video==null){
            return new FailedResponse(cmd, "Video with given ID doesnt exist");
        }
        List<Category> results = em.createQuery("SELECT b.idC FROM Belongs b WHERE b.idV.idV = :idV", Category.class).setParameter("idV", data.getVideoId()).getResultList();
        return new DataResponse<>(cmd, results);
    }
    
}
