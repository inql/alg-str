package com.inql;

public class Surname {

    private int count;
    private String surname;

    public Surname(int count, String surname) {
        this.count = count;
        this.surname = surname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "{" +
                "count=" + count +
                ", surname='" + surname + '\'' +
                '}';
    }
}
