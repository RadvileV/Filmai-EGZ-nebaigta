package com.example.filmai.model;

import java.sql.*;

public class UserDAO {

    public static void create (User user) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "INSERT INTO `users`(`username`, `password`, `email`, `isAdmin`) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setBoolean(4, user.isAdmin());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko sukurti naują vartotoją.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Įvyko klaida kuriant naują vartotoją.");
        }
    }

    public static String getBCryptPassword(String username) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "SELECT password FROM `users` WHERE `username` = ?";
        String passwordBCrypted = "";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passwordBCrypted = resultSet.getString("password");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passwordBCrypted;
    }

    public static boolean userIsAdmin(String username, String password) {
        boolean status = false;
        try{
            String jdbcUrl = "jdbc:mysql://localhost:3306/db";
            String querry = "SELECT * FROM `users` WHERE username=? and password=? and isAdmin=1";
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, String.valueOf(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return status;
    }

    public static boolean userIsNotAdmin(String username, String password) {
        boolean status = false;
        try{
            String jdbcUrl = "jdbc:mysql://localhost:3306/db";
            String querry = "SELECT * FROM `users` WHERE username=? and password=? and isAdmin=0";
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, String.valueOf(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return status;
    }

    public static String searchByUsername(String username) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "";
        querry = "SELECT * FROM `users` WHERE `username` = ?";

        String username2 = "";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                username2 = resultSet.getBoolean("isAdmin") ? "ADMINISTRATORIUS" : "VARTOTOJAS";
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username2;
    }
}

