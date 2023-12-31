package com.csc325Team5.payrollapplication.model;

import com.csc325Team5.payrollapplication.utilities.Role;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class PayStubManager {
    private LinkedList<PayStub> payStubs = new LinkedList<>();

    public void addPayStub(User user){
        AtomicReference<User> newUser = new AtomicReference<User>();
        UserManager.getUserBag().forEach(e->{
            if(e.compareTo(user) == 0){
                newUser.set(user);
            }
        });

        double totalPay = calculatePay(newUser.get().getHoursWorkedThisWeek(),newUser.get().getSalary());
        payStubs.add(
                new PayStub(
                        startPayPeriod()
                        ,endPayPeriod()
                        ,user.getHoursWorkedThisWeek()
                        ,user.getSalary()
                        ,totalPay
                        ,Role.valueOf(user.getRole().toUpperCase())
                        ,user
                        ,String.valueOf(round(user.getSalary()))
                )
        );
        user.setHoursWorkedThisWeek(0.0);
    }

    public double round(double number){
        return (double) Math.round(number * 1000) /1000;
    }

    public String startPayPeriod(){
        LocalDate currentDate = LocalDate.now();
        DayOfWeek currentDayOfWeek = currentDate.getDayOfWeek();
        int daysFromMonday = DayOfWeek.MONDAY.getValue() - currentDayOfWeek.getValue();
        Month currentMonth = currentDate.getMonth();
        int currentYear = currentDate.getYear();
        return currentMonth.getValue() + "/" + (currentDate.getDayOfMonth() + daysFromMonday) + "/" +currentYear;
    }

    public String endPayPeriod(){
        LocalDate currentDate = LocalDate.now();
        DayOfWeek currentDayOfWeek = currentDate.getDayOfWeek();
        int daysFromSunday = DayOfWeek.SUNDAY.getValue() - currentDayOfWeek.getValue();
        Month currentMonth = currentDate.getMonth();
        int currentYear = currentDate.getYear();
        return currentMonth.getValue() + "/" + (currentDate.getDayOfMonth() + daysFromSunday) + "/" +currentYear;
    }
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
        return hoursWorked * hourlyRate;
    }

    public void removePayStub(PayStub payStub){
        this.payStubs.remove(payStub);
    }

    public LinkedList<PayStub> getPayStubs(){
        return payStubs;
    }
}
