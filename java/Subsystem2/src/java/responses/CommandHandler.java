/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responses;

/**
 *
 * @author Jelena
 */
import javax.persistence.EntityManager;
import commands.Command;

public abstract class CommandHandler {
    protected final EntityManager em;
    public CommandHandler(EntityManager em) {
        this.em = em;
    }
    abstract public JMSResponse handle(Command cmd);
}
