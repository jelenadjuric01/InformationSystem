/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responses;

import commands.Command;

/**
 *
 * @author Jelena
 */
public class DataResponse<T> extends ValidResponse {
    private final T data;
    public DataResponse(Command cmd, T data) {
        super(cmd);
        this.data = data;
    }
    public T getData() {
        return data;
    }
}
