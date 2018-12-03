package com.sylwesteroleszek.entity;

import javax.persistence.*;

public class NewUser {

    private Long id;
    private String username;
    private String password;
    private Long totalCashSpend;
    private String name;
    private String surname;
    private String role;

    public NewUser() {
    }

    public NewUser(Long id, String name, String surname, String username, String password, String role, Long totalCashSpend) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.totalCashSpend = totalCashSpend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getTotalCashSpend() {
        return totalCashSpend;
    }

    public void setTotalCashSpend(Long totalCashSpend) {
        this.totalCashSpend = totalCashSpend;
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", totalCashSpend=" + totalCashSpend +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
