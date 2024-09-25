/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class CreateRatingCommand extends Command {
    private final int rate;
    private final int userId;
    private final int videoId;

    public CreateRatingCommand(int rate, int userId, int videoId) {
        this.rate = rate;
        this.userId = userId;
        this.videoId = videoId;
    }

    public int getRate() {
        return rate;
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
        return Type.CREATE_RATING;
    }
    
}
