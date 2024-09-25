/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client.rest;

/**
 *
 * @author Jelena
 */

public class Town {
    public int idT;
    public String name;

    public int getIdT() {
        return idT;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Town:" + "IdT=" + idT + ", Name=" + name ;
    }
    
}
