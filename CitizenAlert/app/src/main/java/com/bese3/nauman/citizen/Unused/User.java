package com.bese3.nauman.citizen.Unused;

import java.io.Serializable;

/**
 * Created by Nauman on 1/7/2015.
 */
public class User implements Serializable, Comparable<User> {
    private String username;
    private String password;
    private String email;
    private String cnic;
    private String cellno;
    private String profileImage;
    private Gender gender;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getCellno() {
        return cellno;
    }

    public void setCellno(String cellno) {
        this.cellno = cellno;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(User user) {
        return this.getUsername().compareTo(user.getUsername());
    }

    @Override
    public boolean equals(Object o) {
        return this.getUsername().equals(((User)o).getUsername());
    }

    public enum Gender implements Serializable {
        MALE,FEMALE
    }
}