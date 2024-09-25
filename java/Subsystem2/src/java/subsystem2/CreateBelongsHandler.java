/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem2;

import commands.Command;
import commands.CreateBelongsCommand;
import commands.CreateCategoryCommand;
import entities.Belongs;
import entities.Category;
import entities.User;
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
public class CreateBelongsHandler extends CommandHandler{
    public CreateBelongsHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        CreateBelongsCommand data = (CreateBelongsCommand)cmd;
        Video video = em.find(Video.class, data.getVideoId());
        if(video==null){
            return new FailedResponse(cmd, "Video with ID doesnt exist");
        }
        Category category = em.find(Category.class,data.getCatId());
        if(category==null){
            return new FailedResponse(cmd, "Category with ID doesnt exist");
        }
        List<Belongs> b = em.createQuery("Select b from Belongs b where b.idV.idV=:idV and b.idC.idC=:idC", Belongs.class).setParameter("idV", video.getIdV()).setParameter("idC",category.getIdC()).getResultList();
        if(!b.isEmpty()){
             return new FailedResponse(cmd, "This video is alreafy in this category");
           
        } 
        Belongs belongs= new Belongs();
        belongs.setIdC(category);
        belongs.setIdV(video);
        em.getTransaction().begin();
        em.persist(belongs);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, belongs);
    }
    
}
