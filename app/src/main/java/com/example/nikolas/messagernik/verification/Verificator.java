package com.example.nikolas.messagernik.verification;

/**
 * Created by User on 02.10.2015.
 */
public class Verificator {
    private String password;
    private String confirmPassword;
    private String login;
    private String firstName;
    private String lastName;

    public Verificator(String password, String confirmPassword, String login, String firstName, String lastName) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Verificator() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public boolean validate()
    {
        if(equelsPassword())
        {
            if(!password.equals("") || !confirmPassword.equals("") || !login.equals("") || !firstName.equals("") || !lastName.equals(""))
            {
                return true;
            }
            else return false;
        }
        return false;
    }
    public boolean equelsPassword()
    {
        if (password.equals(confirmPassword)) return true;
        else return false;
    }
}
