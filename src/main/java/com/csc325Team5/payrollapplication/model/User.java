package com.csc325Team5.payrollapplication.model;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

public class User implements Comparable<User>{

    private String name;
    private String username;
    private String password;
    private int age;
    private String ID;
    private int salary;
    private String role;
    private double hoursWorkedThisWeek = 0.0;
    private boolean clockedIn;
    private String clockInTime;
    private String clockOutTime;
    private NoteManager notes = new NoteManager();
    private PayStubManager payStubs = new PayStubManager();

    public User(String name, String username, String password, int age, int salary, String role, String ID) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.ID = ID;
        this.salary = salary;
        this.role = role;
    }
    public User() {
        this.name = "";
        this.username = "";
        this.password = "";
        this.age = 0;
        this.ID = "";
        this.salary = 0;
        this.role = "";
    }

    public NoteManager getNoteManager() {
        return notes;
    }

    public void setNoteManager(NoteManager notes) {
        this.notes = notes;
    }

    /*public void addNote(User sender,String note,User receiver){
        notes.add(new Note(sender,note,getCurrentTime(),receiver));
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public void removeNote(Note note){
        notes.remove(note);
    }

    public LinkedList<Note> getNotes(){
        return notes;
    }*/

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
        this.username = username;
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
    
    public Double getHoursWorkedThisWeek() {
        return hoursWorkedThisWeek;
    }

    public void setHoursWorkedThisWeek(Double hoursWorkedThisWeek) {
        this.hoursWorkedThisWeek = hoursWorkedThisWeek;
    }

    public PayStubManager getPayStubManager() {
        return payStubs;
    }

    public void setPayStubManager(PayStubManager payStubs) {
        this.payStubs = payStubs;
    }

    //Returns the current time in "HH:mm:ss" format as a string in 24-hour format
    public String getCurrentTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date);
    }
    //Method for calculating the clock-in and clock-out time. Returns the time as String
    public double getTimeDifference(String start_time, String end_time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(start_time);
        Date date2 = format.parse(end_time);
        double difference = date2.getTime() - date1.getTime();

        double differenceSeconds = difference / 1000 % 60;
        double differenceMinutes = difference / (60 * 1000) % 60;
        double differenceHours = difference / (60 * 60 * 1000) % 24;

        double hours = differenceHours+differenceMinutes/60+differenceSeconds/3600;
        double roundedHours = (double) Math.round(hours * 10000)/10000;
        return roundedHours;
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
    // Calculate pay based on hours worked and hourly rate
    public double calculatePay(double hoursWorked, double hourlyRate) {
        // Ensure hours worked is non-negative
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative.");
        }

        // Ensure hourly rate is non-negative
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        }

        // Calculate pay
        double pay = hoursWorked * hourlyRate;
        return pay;
    }

    // Generate a pay stub for the employee
    public void generatePayStub(double hoursWorked, double hourlyRate) {
        // Calculate the pay
        double pay = calculatePay(hoursWorked, hourlyRate);

        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Hourly Rate: $" + hourlyRate);
        System.out.println("Total Pay: $" + pay);
        System.out.println("Role: " + getRole());
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
        return name + " : ID = " + ID ;
    }

    @Override
    public int compareTo(User o) {
        return Integer.compare(this.getID().compareTo(o.getID()), 0);

    }
}
