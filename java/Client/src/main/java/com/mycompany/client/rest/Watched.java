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
public class Watched {
    private int idW;
    private Date dateTime;
    private int startSecond;
    private int secondsWatched;
    private User idU;
    private Video idV;

    @Override
    public String toString() {
        return "Watched{" + "IdW=" + idW + ", DateTime=" + dateTime + ", StartSecond=" + startSecond + ", SecondsWatched=" + secondsWatched + ", user=" + idU.getIdU()+" ,"+idU.getName()+" ,"+idU.getLastname() + '}';
    }

   
    
}
