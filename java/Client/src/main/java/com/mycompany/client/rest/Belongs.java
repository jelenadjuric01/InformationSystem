/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client.rest;

/**
 *
 * @author Jelena
 */
public class Belongs {
    private Video idV;
    private Category idC;
    private int idB;

    @Override
    public String toString() {
        return "Belongs:" + "IdV=" + idV.getIdV()+" ,"+idV.getName() + ", IdC=" + idC;
    }
    
}
