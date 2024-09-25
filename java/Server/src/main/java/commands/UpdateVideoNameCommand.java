/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class UpdateVideoNameCommand extends Command {
    private final int videoId;
    private final String name;

    public UpdateVideoNameCommand(int videoId, String name) {
        this.videoId = videoId;
        this.name = name;
    }

    public int getVideoId() {
        return videoId;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S2;
    }

    @Override
    public Type getType() {
        return Type.UPDATE_VIDEO_NAME;
    }
    
}
