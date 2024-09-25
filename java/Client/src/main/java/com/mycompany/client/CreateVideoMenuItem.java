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
public class CreateVideoMenuItem extends MenuItem{

    @Override
    public String name() {
        return "Create video";
    }

    @Override
    public Call<?> execute(Scanner scanner, Service service) {
        String name = readString(scanner, "Name");
        int userId = readInt(scanner, "User id");
        int duration = readInt(scanner, "Duration");
        return service.createVideo(userId,name,duration);
    }
    
}
