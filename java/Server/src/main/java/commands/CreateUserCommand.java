/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 *
 * @author Jelena
 */
public class CreateUserCommand extends Command {
    private final String name;
    private final int townId;
    private final String lastname;
    private final String email;
    private final int year;
    private final Character sex;

    public CreateUserCommand(String name, int townId, String lastname, String email, int year, Character sex) {
        this.name = name;
        this.townId = townId;
        this.lastname = lastname;
        this.email = email;
        this.year = year;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getTownId() {
        return townId;
    }

    public String getLastname() {
        return lastname;
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
    
    @Override
    public Endpoint getEndpoint() {
        return Endpoint.S1;
    }

    @Override
    public Type getType() {
        return Type.CREATE_USER;
    }
    
}
