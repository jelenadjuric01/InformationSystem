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
public class Video {
    private int idV;
    private String name;
    private String duration;
    private Date dateTime;
    private User idU;

    public int getIdV() {
        return idV;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public User getIdU() {
        return idU;
    }

    @Override
    public String toString() {
        return "Video{" + "IdV=" + idV + ", Name=" + name + ", Duration=" + duration + ", DateTime=" + dateTime + ",owner=" + idU.getIdU()+" ,"+idU.getName()+" ,"+idU.getLastname() + '}';
    }

   
    
}
