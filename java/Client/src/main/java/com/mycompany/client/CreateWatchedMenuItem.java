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
public class CreateWatchedMenuItem extends MenuItem{

    @Override
    public String name() {
        return "Video is watched";
    }

    @Override
    public Call<?> execute(Scanner scanner, Service service) {
        int start = readInt(scanner, "Start second");
        int seconds = readInt(scanner, "Seconds watched");
        int userId = readInt(scanner, "User id");
        int videoId = readInt(scanner, "Video id");

        return service.createWatched(start,seconds,userId,videoId); 
    }
    
}
