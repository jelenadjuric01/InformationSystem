/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client;

/**
 *
 * @author Jelena
 */
import java.util.Scanner;
import retrofit2.Call;
import com.mycompany.client.rest.Service;

public class CreateTownMenuItem extends MenuItem {
    @Override
    public String name() {
        return "Create town";
    }
    @Override
    public Call<?> execute(Scanner scanner, Service service) {
        String name = readString(scanner, "Name");
        return service.createTown(name);
    }
}
