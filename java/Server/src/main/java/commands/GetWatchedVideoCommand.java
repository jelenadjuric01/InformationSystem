/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class GetWatchedVideoCommand extends Command {
    private final int videoId;

    public GetWatchedVideoCommand(int videoId) {
        this.videoId = videoId;
    }

    public int getVideoId() {
        return videoId;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S3;
    }

    @Override
    public Type getType() {
        return Type.GET_WATCHES_VIDEO;
    }
    
}
