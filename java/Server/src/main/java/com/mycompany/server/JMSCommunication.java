/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server;

import commands.Command;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import responses.MultipleResponse;
import responses.FailedResponse;
import responses.JMSResponse;

/**
 *
 * @author Jelena
 */
@Singleton
public class JMSCommunication {
     @Resource(lookup="jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup="queue1")
    private Queue queue1;

    @Resource(lookup="queue2")
    private Queue queue2;

    @Resource(lookup="queue3")
    private Queue queue3;

    @Resource(lookup="queueServer")
    private Queue queueServer;
    
    private Queue[] getEndpoint(Command.Endpoint endpoint) {
        switch (endpoint) {
            case S1: return new Queue[] {queue1};
            case S2: return new Queue[] {queue2};
            case S3: return new Queue[] {queue3};
        }
        return new Queue[] {};
    }
    public JMSResponse exchange(Command cmd) {
        List<JMSResponse> responses = new ArrayList<>();
        try (JMSContext context = connectionFactory.createContext()) {
            for (Queue q : getEndpoint(cmd.getEndpoint())) {
                ObjectMessage objMsg = context.createObjectMessage(cmd);
                try (JMSConsumer consumer = context.createConsumer(queueServer, "JMSCorrelationID = '" + cmd.getId() + "'")) {
                    objMsg.setJMSReplyTo(queueServer);
                    objMsg.setJMSCorrelationID(String.valueOf(cmd.getId()));
                    System.out.println("JMSCommunicator sending " + cmd);
                    JMSProducer producer = context.createProducer();
                    producer.send(q, objMsg);
                    Message recvMsg = consumer.receive(5000);
                    if (!(recvMsg instanceof ObjectMessage)) {
                        System.err.println("Received message is not an ObjectMessage: " + recvMsg);
                        continue;
                    }
                    Serializable obj = ((ObjectMessage)recvMsg).getObject();
                    if (!(obj instanceof JMSResponse)) {
                        System.err.println("Response is not a JMSResponse: " + obj);
                        continue;
                    }
                    System.out.println(obj);
                    responses.add((JMSResponse)obj);
                } catch (JMSException ex) {
                    Logger.getLogger(JMSCommunication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (responses.isEmpty()) {
            return new FailedResponse(cmd, "No responses.");
        }
        if (responses.size() == 1) {
            return responses.get(0);
        }
        return new MultipleResponse(cmd, responses);
    }
}
