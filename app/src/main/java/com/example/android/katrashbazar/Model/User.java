package com.example.android.katrashbazar.Model;

public class User
{
    private String name, phone, password,address, image;

    public User(String name, String phone, String password) {
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.password = password;
        this.address = address;
    }

    public User()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
