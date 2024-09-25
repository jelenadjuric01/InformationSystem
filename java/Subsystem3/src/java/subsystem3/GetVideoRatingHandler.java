/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem3;

import commands.Command;
import commands.GetVideoRatingsCommand;
import commands.GetWatchedVideoCommand;
import entities.Rating;
import entities.Video;
import entities.Watched;
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
public class GetVideoRatingHandler extends CommandHandler {
    public GetVideoRatingHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        GetVideoRatingsCommand data = (GetVideoRatingsCommand)cmd;
        Video video = em.find(Video.class, data.getVideoId());
        if(video==null){
            return new FailedResponse(cmd, "Video with given ID doesnt exist");
        }
        List<Rating> results = em.createQuery("SELECT b FROM Rating b WHERE b.idV.idV = :idU", Rating.class).setParameter("idU", data.getVideoId()).getResultList();
        return new DataResponse<>(cmd, results);
    }
    
}
