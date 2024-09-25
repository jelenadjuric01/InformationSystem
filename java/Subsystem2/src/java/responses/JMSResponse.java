/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responses;

import commands.Command;
import java.io.Serializable;

/**
 *
 * @author Jelena
 */
public abstract class JMSResponse implements Serializable {
    protected final Command cmd;
    public JMSResponse(Command cmd) {
        this.cmd = cmd;
    }
    public String getId() {
        return cmd.getId();
    }
    abstract public boolean isSuccessful();
    @Override
    public String toString() {
        return "Response[id=" + getId() + ", type=" + cmd.getType() + ", from=" + cmd.getEndpoint()+ "]";
    }
}
