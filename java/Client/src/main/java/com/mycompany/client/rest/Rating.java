/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client.rest;

import java.util.Date;

/**
 *
 * @author Jelena
 */
public class Rating {
    private int idR;
    private int rate;
    private Date dateTime;
    private User idU;
    private Video idV;

    @Override
    public String toString() {
        return "Rating{" + "IdR=" + idR + ", Rate=" + rate + ", DateTime=" + dateTime + ", user=" + idU.getIdU()+" ,"+idU.getName()+" ,"+idU.getLastname() +"IdV=" + idV.getIdV()+" ,"+idV.getName() + '}';
    }

    
    
}
