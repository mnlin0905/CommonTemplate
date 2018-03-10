package com.acchain.community.bean;

import java.util.List;

/**
 * Created by rsp on 2018/1/23.
 */

public class PhoneContact {
    private String name;
    private List<String> phoneNumber;
    private String letters;
    private String nameSpelling;

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getNameSpelling() {
        return nameSpelling;
    }

    public void setNameSpelling(String nameSpelling) {
        this.nameSpelling = nameSpelling;
    }

    public PhoneContact() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public PhoneContact(String name, List<String> phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PhoneContact{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", letters='" + letters + '\'' +
                ", nameSpelling='" + nameSpelling + '\'' +
                '}';
    }
}
