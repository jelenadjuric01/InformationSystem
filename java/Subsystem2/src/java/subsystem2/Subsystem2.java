/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package subsystem2;

/**
 *
 * @author Jelena
 */
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import commands.Command;
import responses.CommandHandler;
import responses.JMSResponse;

public class Subsystem2 {
    @Resource(lookup="jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(lookup="queue2")
    private static Queue queue2;

    private static final Map<Command.Type, CommandHandler> handlers = assignHandlers();

    private static Map<Command.Type, CommandHandler> assignHandlers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        Map<Command.Type, CommandHandler> map = new EnumMap<>(Command.Type.class);
        map.put(Command.Type.CREATE_CATEGORY, new CreateCategoryHandler(em));
        map.put(Command.Type.GET_CATEGORIES, new GetCategoriesHandler(em));
        map.put(Command.Type.CREATE_VIDEO, new CreateVideoHandler(em));
        map.put(Command.Type.GET_VIDEOS, new GetVideoHandler(em));
        map.put(Command.Type.UPDATE_VIDEO_NAME, new UpdateVideoNameHandler(em));
        map.put(Command.Type.DELETE_VIDEO, new DeleteVideoHandler(em));
        map.put(Command.Type.ADD_CATEGORY_VIDEO, new CreateBelongsHandler(em));
        map.put(Command.Type.GET_CATEGORY_VIDEO, new GetCategoryVideoHandler(em));

        return map;
    }
    
    public static void main(String[] args) {
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue2);
        Message msg;
        while ((msg = consumer.receiveNoWait()) != null) {
            System.out.println("Discarding message " + msg);
        }
        System.out.println("Subsystem ready...");
        while (true) {
            try {
                msg = consumer.receive();
                Destination replyTo = msg.getJMSReplyTo();
                if (replyTo == null || !(replyTo instanceof Queue)) {
                    System.err.println("Reply destination is invalid: " + msg);
                    continue;
                }
                if (!(msg instanceof ObjectMessage)) {
                    System.err.println("Message is not an ObjectMessage: " + msg);
                    continue;
                }
                Serializable obj = ((ObjectMessage)msg).getObject();
                if (!(obj instanceof Command)) {
                    System.err.println("Received object is not a command: " + obj);
                    continue;
                }
                Command cmd = (Command)obj;
                CommandHandler handler = handlers.get(cmd.getType());
                if (handler == null) {
                    System.err.println("No handler found for command: " + cmd);
                    continue;
                }
                System.out.println(cmd);
                JMSResponse response = handler.handle(cmd);
                ObjectMessage responseMsg = context.createObjectMessage(response);
                responseMsg.setJMSCorrelationID(msg.getJMSCorrelationID());
                System.out.println(response);
                producer.send(replyTo, responseMsg);
            } catch (Exception ex) {
                Logger.getLogger(Subsystem2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
