/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responses;

import commands.Command;

/**
 *
 * @author Jelena
 */
public abstract class ValidResponse extends JMSResponse {
    public ValidResponse(Command cmd) {
        super(cmd);
    }
    @Override
    public boolean isSuccessful() {
        return true;
    }
    @Override
    public String toString() {
        return "SuccessfulResponse[id=" + getId() + ", type=" + cmd.getType() + ", from=" + cmd.getEndpoint()+ "]";
    }
}
