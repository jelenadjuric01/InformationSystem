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
public class CreateUserMenuItem extends MenuItem {

    @Override
    public String name() {
        return "Create user";
    }

    @Override
    public Call<?> execute(Scanner scanner, Service service) {
        String name = readString(scanner, "Name");
        int townId = readInt(scanner, "Town id");
        String lastname = readString(scanner, "Lastname");
        String email = readString(scanner, "Email");
        int year = readInt(scanner, "Year");
        String sex = readString(scanner, "Sex");

        return service.createUser(townId, name, lastname, email, year,sex.charAt(0));
    }
    
}
