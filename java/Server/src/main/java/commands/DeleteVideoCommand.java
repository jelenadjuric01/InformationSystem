/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class DeleteVideoCommand extends Command {
    private final int videoId;
    private final int userId;

    public DeleteVideoCommand(int videoId, int userId) {
        this.videoId = videoId;
        this.userId = userId;
    }
   

    public int getVideoId() {
        return videoId;
    }

    public int getUserId() {
        return userId;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S2;
    }

    @Override
    public Type getType() {
        return Type.DELETE_VIDEO;
    }
    
}
