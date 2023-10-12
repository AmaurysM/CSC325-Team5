package com.example.payrollapplication.Util;

import java.util.TreeSet;
import java.util.stream.Stream;

public class UserBag {
    private static int numOfUsers = 0;
    private static User currentUser;
    private static TreeSet<User> userBag = new TreeSet<>();

    //String name, String username, String password, int age, int ID, int salary, String role
    public static void createUser(String name, String username, String password, int age, String ID, int salary, String role){
        userBag.add(new User(name,username,password,age,ID,salary,role));
        numOfUsers++;
    }
    public static void addUser(User user){
        userBag.add(user);
    }

    public static Stream<User> findUserByName(String name){
        return userBag.stream().filter(u-> u.getName().equals(name));
    }

    public static User findUserByID(String ID){
        Stream<User> foundUsers = userBag.stream().filter(u-> u.getName().equals(ID));
        if(foundUsers.toList().isEmpty()){
            return null;
        }else{
            return userBag.stream().filter(u-> u.getName().equals(ID)).toList().get(0);
        }

    }

    public static void removeUser(User user){
        userBag.remove(user);
    }

    public static void printUsers(){userBag.forEach(e -> System.out.println(e.getName() + "\n"));}

    public static int getNumberOfUsers(){
        return numOfUsers;
    }

    public static TreeSet<User> getUserBag(){
        return userBag;
    }
}
