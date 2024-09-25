/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client;

import com.mycompany.client.rest.Service;
import java.util.Scanner;
import retrofit2.Call;

/**
 *
 * @author Jelena
 */
public class DeleteVideoMenuItem extends MenuItem{

    @Override
    public String name() {
        return "Delete video";
    }

    @Override
    public Call<?> execute(Scanner scanner, Service service) {
         int videoId = readInt(scanner, "Video id");
         int userId = readInt(scanner, "User id");
        return service.deleteVideo(videoId,userId);
    }
    
}
