/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class CreateWatchedCommand extends Command {
    private final int start;
    private final int seconds;
    private final int userId;
    private final int videoId;

    public CreateWatchedCommand(int start, int seconds, int userId, int videoId) {
        this.start = start;
        this.seconds = seconds;
        this.userId = userId;
        this.videoId = videoId;
    }

    public int getStart() {
        return start;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getUserId() {
        return userId;
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
        return Type.CREATE_WATCHED;
    }
    
}
