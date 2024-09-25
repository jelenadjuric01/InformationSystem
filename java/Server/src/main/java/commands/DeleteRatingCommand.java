/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class DeleteRatingCommand extends Command {
    private final int ratingId;

    public DeleteRatingCommand(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getRatingId() {
        return ratingId;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S3;
    }

    @Override
    public Type getType() {
        return Type.DELETE_RATING;
    }
    
}
