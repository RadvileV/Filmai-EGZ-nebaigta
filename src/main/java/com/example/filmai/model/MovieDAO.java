package com.example.filmai.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {


    public static List<Movie> searchByName(String title) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "";
        if (title.isEmpty()) {
            querry = "SELECT * FROM `movies`";
        } else {
            querry = "SELECT * FROM `movies` WHERE `title` LIKE '%" + title + "%'";
        }
        ArrayList<Movie> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //Kol turime sarase elementus
                list.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating"),
                        resultSet.getString("category")
                ));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    public static Movie searchById(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "SELECT * FROM `movies` WHERE `id` = ?";
        ArrayList<Movie> list = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating"),
                        resultSet.getString("category")
                ));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } try {
            return list.get(0);
        }catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static void update(Movie movie) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "UPDATE `books` SET `title`= ?,`description`= ?, `rating`= ?,`category`= ? WHERE `id`= ?";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getDescription());
            preparedStatement.setDouble(3, movie.getRating());
            preparedStatement.setString(4, movie.getCategory());
            preparedStatement.setInt(5, movie.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko atnaujinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko atnaujinti įrašo");
        }
    }


    public static void create(Movie movie) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "INSERT INTO `movies`(`title`, `description`, `rating`, `category`) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getDescription());
            preparedStatement.setDouble(3, movie.getRating());
            preparedStatement.setString(4, movie.getCategory());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteById(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "DELETE FROM `movies` WHERE `id` = ?";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko ištrinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Įrašo ištrinti nepavyko");
        }
    }
}

