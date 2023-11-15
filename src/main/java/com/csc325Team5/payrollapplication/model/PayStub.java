package com.csc325Team5.payrollapplication.model;

import com.csc325Team5.payrollapplication.utilities.Role;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class PayStub {

    private User user;
    private String salary;
    private String startPayPeriod;
    private String endPayPeriod;
    private double hoursWorked;
    private double hourlyRate;
    private double totalPay;
    private LocalDate timeCreated;
    private Role role;

    public PayStub(String startPayPeriod, String endPayPeriod, double hoursWorked, double hourlyRate, double totalPay, Role role, User user, String salary) {
        this.startPayPeriod = startPayPeriod;
        this.endPayPeriod = endPayPeriod;
        this.hoursWorked = hoursWorked;
        timeCreated = LocalDate.now();
        this.hourlyRate = hourlyRate;
        this.totalPay = totalPay;
        this.role = role;
        this.user = user;
        this.salary = salary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public LocalDate getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDate timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getStartPayPeriod() {
        return startPayPeriod;
    }

    public void setStartPayPeriod(String startPayPeriod) {
        this.startPayPeriod = startPayPeriod;
    }

    public String getEndPayPeriod() {
        return endPayPeriod;
    }

    public void setEndPayPeriod(String endPayPeriod) {
        this.endPayPeriod = endPayPeriod;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "PayStub{" +
                "startPayPeriod='" + startPayPeriod + '\'' +
                ", endPayPeriod='" + endPayPeriod + '\'' +
                ", hoursWorked=" + hoursWorked +
                ", hourlyRate=" + hourlyRate +
                ", totalPay=" + totalPay +
                ", role=" + role +
                '}';
    }
}
