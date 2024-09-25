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

public abstract class MenuItem {
    public abstract String name();
    public abstract Call<?> execute(Scanner scanner, Service service);
    protected int readInt(Scanner scanner, String prompt) {
        System.out.println(prompt + ":");
        return Integer.parseInt(scanner.nextLine());
    }
    protected String readString(Scanner scanner, String prompt) {
        System.out.println(prompt + ":");
        return scanner.nextLine();
    }
}
