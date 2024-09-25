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
public class Subscription {
    private int idS;
    private Date dateTime;
    private int price;
    private Package idP;
    private User idU;

    @Override
    public String toString() {
        return "Subscription{" + "IdS=" + idS + ", DateTime=" + dateTime + ", Price=" + price + ", package=" + idP + '}';
    }

    
    
}
