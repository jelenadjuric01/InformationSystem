/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class CreateBelongsCommand extends Command {
    private final int videoId;
    private final int catId;

    public CreateBelongsCommand(int videoId, int catId) {
        this.videoId = videoId;
        this.catId = catId;
    }

    public int getVideoId() {
        return videoId;
    }

    public int getCatId() {
        return catId;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S2;
    }

    @Override
    public Type getType() {
        return Type.ADD_CATEGORY_VIDEO;
    }
    
}
