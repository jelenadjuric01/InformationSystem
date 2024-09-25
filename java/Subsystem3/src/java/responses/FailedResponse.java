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
public class FailedResponse extends JMSResponse {
    private final String reason;
    public FailedResponse(Command cmd, String reason) {
        super(cmd);
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }
    @Override
    public boolean isSuccessful() {
        return false;
    }
    @Override
    public String toString() {
        return "FailureResponse[id=" + getId() + ", type=" + cmd.getType() + ", from=" + cmd.getEndpoint() + reason + "]";
    }
}