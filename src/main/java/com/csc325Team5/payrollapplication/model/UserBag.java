package com.csc325Team5.payrollapplication.model;

import com.csc325Team5.payrollapplication.utilities.Role;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Stream;

public class UserBag {
    private static int numOfUsers = 0;
    private static User currentUser;
    private static TreeSet<User> userBag = new TreeSet<>();

    //private static final String roles;

    //String name, String username, String password, int age, String ID, int salary, String role
    public static void createUser(String name, String username, String password, int age, int salary, String role){
        User user = new User(name,username,password,age,salary,role,createID());
        userBag.add(user);
        numOfUsers++;
    }

 /*   public static boolean ableToCreateUser(String name, String username, String password, int age, int salary, String role){
        System.out.println(acceptableRole(role));
        if(acceptableRole()){}

    }*/

    public static boolean acceptableRole(String role){
        return Arrays.stream(Role.values()).anyMatch(e -> e.name().compareTo(role) == 0);
    }

    public static Stream<User> findUserByName(String name){
        return userBag.stream().filter(u-> u.getName().equals(name));
    }

    public static String createID(){
        String tempID = randomNumber(1000) + "-"  + randomNumber(1000) + "-" + randomNumber(1000);
        if(!checkIDUniqueness(tempID)){
            createID();
        }
        return tempID;
    }

    public static boolean checkIDUniqueness(String ID){
        return UserBag.findUserByID(ID) == null;
    }

    public static String randomNumber(int max){
        return String.valueOf((int) Math.floor(Math.random() * max));
    }

    public static User findUserByID(String ID){
        Stream<User> foundUsers = userBag.stream().filter(u-> u.getName().equals(ID));

        if(foundUsers.toList().isEmpty()){
            return null;
        }else{
            return userBag.stream().filter(u-> u.getName().equals(ID)).toList().get(0);
        }

    }

    public static User findUser(User user){
        List<User> foundUsers = UserBag.getUserBag().stream().filter(e->e.equals(user)).toList();
        if(foundUsers.isEmpty()){
            return null;
        }else{
            return foundUsers.get(0);
        }
    }

    public static void removeUser(User user){
        userBag.remove(user);
    }

    public static void printUsers(){userBag.forEach(e -> System.out.println(e.getName() + "\n"));}

    public static int getNumOfUsers() {
        return numOfUsers;
    }

    public static void setNumOfUsers(int numOfUsers) {
        UserBag.numOfUsers = numOfUsers;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserBag.currentUser = currentUser;
    }

    public static TreeSet<User> getUserBag() {
        return userBag;
    }

    public static void setUserBag(TreeSet<User> userBag) {
        UserBag.userBag = userBag;
    }
}
