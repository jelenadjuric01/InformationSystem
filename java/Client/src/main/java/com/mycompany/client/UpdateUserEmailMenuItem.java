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
public class UpdateUserEmailMenuItem extends MenuItem{

    @Override
    public String name() {
        return "Update user's email";
    }

    @Override
    public Call<?> execute(Scanner scanner, Service service) {
        String name = readString(scanner, "Email");
        int userId = readInt(scanner, "User id");
        return service.updateUsersEmail(userId,name);   
    }
    
}
