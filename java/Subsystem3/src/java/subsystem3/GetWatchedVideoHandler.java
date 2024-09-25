/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem3;

import commands.Command;
import commands.GetSubUserCommand;
import commands.GetWatchedVideoCommand;
import entities.Subscription;
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
public class GetWatchedVideoHandler extends CommandHandler {
    public GetWatchedVideoHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        GetWatchedVideoCommand data = (GetWatchedVideoCommand)cmd;
        Video video = em.find(Video.class, data.getVideoId());
        if(video==null){
            return new FailedResponse(cmd, "Video with given ID doesnt exist");
        }
        List<Watched> results = em.createQuery("SELECT b FROM Watched b WHERE b.idV.idV = :idU", Watched.class).setParameter("idU", data.getVideoId()).getResultList();
        return new DataResponse<>(cmd, results);
    }
    
}
