package com.csc325Team5.payrollapplication.model;

import com.csc325Team5.payrollapplication.utilities.Role;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class User implements Comparable<User>{



    private String name;
    private String username;
    private String password;
    private int age;
    private String ID;
    private int salary;
    private String role;
    private boolean clockedIn;
    private String clockInTime;
    private String clockOutTime;
    private ArrayList<Note> notes = new ArrayList<>();

    public User(String name, String username, String password, int age, int salary, String role, String ID) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.ID = ID;
        this.salary = salary;
        this.role = role;
    }


    public void addNote(User sender,String note,User receiver){
        notes.add(new Note(sender,note,getCurrentTime(),receiver));
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public void removeNote(Note note){
        notes.remove(note);
    }

    public ArrayList<Note> getNotes(){
        return notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username;
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
        return
                name +
                ", ID=" + ID +
                ", role='" + role + '\'';
    }

    @Override
    public int compareTo(User o) {
        return Integer.compare(this.getID().compareTo(o.getID()), 0);

    }
}
