package com.example.authenticationfirebase;

public class User {
    private String name,email,phno,password;
    public User(){}
    public User(String name, String email,String phno, String password){
        this.name = name;
        this.email = email;
        this.phno = phno;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phno;
    }

    public void setPhone(String phno) {
        this.phno = phno;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

}


