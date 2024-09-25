/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem2;

import commands.Command;
import commands.GetCategoriesCommand;
import commands.GetVideoCategoryCommand;
import entities.Belongs;
import entities.Category;
import java.util.List;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
public class GetCategoriesHandler extends CommandHandler {
    public GetCategoriesHandler(EntityManager em) {
        super(em);
    }
    
    
    @Override
    public JMSResponse handle(Command cmd) {
        List<Category> results = em.createNamedQuery("Category.findAll", Category.class).getResultList();
        return new DataResponse<>(cmd, results);
    }
}
