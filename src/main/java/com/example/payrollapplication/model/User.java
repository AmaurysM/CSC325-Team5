package com.example.payrollapplication.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TreeSet;

public class User implements Comparable<User>{


    private String name;
    private String Username;
    private String password;
    private int age;
    private String ID;
    private int salary;
    private String role;
    private boolean clockedIn;
    private String clockInTime;
    private String clockOutTime;
    private static TreeSet<Note> notes = new TreeSet<>();


    public User(String name, String username, String password, int age, int salary, String role) {
        this.name = name;
        Username = username;
        this.password = password;
        this.age = age;
        this.ID = String.valueOf(Math.floor(Math.random() * 1000));
        this.salary = salary;
        this.role = role;
    }

    public void addNote(String note){
        notes.add(new Note(note,getCurrentTime()));
    }

    public void removeNote(String note){
        notes.remove(new Note(note,getCurrentTime()));
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

    public boolean isClockedIn() {
        return clockedIn;
    }

    public void setClockedIn(boolean clockedIn) {
        this.clockedIn = clockedIn;
    }

    public String getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(String clockInTime) {
        this.clockInTime = clockInTime;
    }

    public String getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(String clockOutTime) {
        this.clockOutTime = clockOutTime;
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
