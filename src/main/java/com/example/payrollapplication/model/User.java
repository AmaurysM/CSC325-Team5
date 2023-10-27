package com.example.payrollapplication.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class User implements Comparable<User>{
    private String name;
    private String Username;
    private String password;
    private int age;
    private String ID;
    private int salary;
    private String role;

    public User(String name, String username, String password, int age, String ID, int salary, String role) {
        this.name = name;
        Username = username;
        this.password = password;
        this.age = age;
        this.ID = ID;
        this.salary = salary;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    //Returns the current time in "HH:mm:ss" format as a string in 24-hour format
    public String getCurrentTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date);
    }
    //Method for calculating the clock-in and clock-out time. Returns the time as String
    public String getTimeDifference(String start_time, String end_time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(start_time);
        Date date2 = format.parse(end_time);
        long difference = date2.getTime() - date1.getTime();

        long differenceSeconds = difference / 1000 % 60;
        long differenceMinutes = difference / (60 * 1000) % 60;
        long differenceHours = difference / (60 * 60 * 1000) % 24;
        return differenceHours+":"+differenceMinutes+":"+differenceSeconds;
    }
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return getAge() == user.getAge() && getID() == user.getID() && Objects.equals(getName(), user.getName()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getName(), getUsername(), getPassword(), getAge(), getID());
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", Username='" + Username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", ID=" + ID +
                ", salary=" + salary +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public int compareTo(User o) {
        if(this.getID().compareTo(o.getID())>0){
            return 1;
        }else if ( this.getID().compareTo(o.getID())<0){
            return -1;
        }else {
            return 0;
        }

    }
}
