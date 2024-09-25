/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client.rest;

/**
 *
 * @author Jelena
 */
public class User {
    private int idU;
    private String lastname;
    private String name;
    private String email;
    private int year;
    private Character sex;
    private Town idT;

    public int getIdU() {
        return idU;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getYear() {
        return year;
    }

    public Character getSex() {
        return sex;
    }

    public Town getIdT() {
        return idT;
    }
    
    @Override
    public String toString() {
        return "User{" + "IdU=" + idU + ", Lastname=" + lastname + ", Name=" + name + ", Email=" + email + ", Year=" + year + ", Sex=" + sex + "," + idT + '}';
    }
    
    
    
}
