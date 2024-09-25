/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class UpdateRatingRateCommand extends Command {
    private final int ratingdId;
    private final int rate;

    public UpdateRatingRateCommand(int ratingdId, int rate) {
        this.ratingdId = ratingdId;
        this.rate = rate;
    }

    public int getRatingdId() {
        return ratingdId;
    }

    public int getRate() {
        return rate;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S3;
    }

    @Override
    public Type getType() {
        return Type.UPDATE_RATING_VIDEO;
    }
    
}
