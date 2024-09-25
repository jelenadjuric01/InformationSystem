/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client.rest;

/**
 *
 * @author Jelena
 */
import java.util.UUID;

public class Response {
    public UUID id;
    public boolean successful;
    public String reason;
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request");
        if (successful) {
            sb.append(" was successful.");
        } else {
            sb.append(" was not successful.");
            sb.append(reason);
        }
        return sb.toString();
    }
}
