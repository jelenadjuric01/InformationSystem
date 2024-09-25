/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsystem2;

import commands.Command;
import commands.CreateCategoryCommand;
import entities.Category;
import javax.persistence.EntityManager;
import responses.CommandHandler;
import responses.DataResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
public class CreateCategoryHandler extends CommandHandler{
    public CreateCategoryHandler(EntityManager em) {
        super(em);
    }
    @Override
    public JMSResponse handle(Command cmd) {
        CreateCategoryCommand data = (CreateCategoryCommand)cmd;
         Category category = new Category();
        category.setName(data.getName());
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
        em.clear();
        return new DataResponse<>(cmd, category);
    }
}
