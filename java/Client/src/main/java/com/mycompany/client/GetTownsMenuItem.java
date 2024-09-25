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
public class GetTownsMenuItem extends MenuItem{

    @Override
    public String name() {
        return "Get all towns";
    }

    @Override
    public Call<?> execute(Scanner scanner, Service service) {
        return service.towns();
    }
    
}
