/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responses;

import commands.Command;
import java.util.List;

/**
 *
 * @author Jelena
 */
public class MultipleResponse extends JMSResponse {
    private final List<JMSResponse> responses;
    public MultipleResponse(Command cmd, List<JMSResponse> responses) {
        super(cmd);
        this.responses = responses;
    }
    public List<JMSResponse> getResponses() {
        return responses;
    }
    @Override
    public boolean isSuccessful() {
        return responses.stream().noneMatch(r -> !r.isSuccessful());
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AggregateResponse[");
        for (int i = 0; i < responses.size(); ++i) {
            sb.append(i);
            sb.append("=");
            sb.append(responses.get(i));
            if (i != responses.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}