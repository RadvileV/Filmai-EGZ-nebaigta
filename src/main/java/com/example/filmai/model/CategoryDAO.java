package com.example.filmai.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public static void create(Category category) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "INSERT INTO `categories`(`name`) VALUES (?)";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko sukurti naują kategoriją.");
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Įvyko klaida kuriant naują kategoriją.");
        }
    }

    public static void update(Category category) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String update = "UPDATE `categories` SET `name`= ? WHERE `id` = ?";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setInt(2, category.getCategoryId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko atnaujinti kategoriją");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko atnaujinti kategorijos");
        }
    }

    public static void deleteById(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String delete = "DELETE FROM `categories` WHERE `id` = ?";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko ištrinti kategoriją");
        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("Kategorijos ištrinti nepavyko");
        }
    }

    public static List<Category> searchByName(String name) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "";
        if (name.isEmpty()) {
            querry = "SELECT * FROM `categories`";
        } else {
            querry = "SELECT * FROM `categories` WHERE `name` LIKE '%" + name + "%'";
        }
        ArrayList<Category> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //Kol turime sarase elementus
                list.add(new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Paieška įvykdyta sėkmingai");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Paieškos atlikti nepavyko");

        }
        return list;
    }

    public static List<String> fullCategoryList() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "SELECT `name` FROM `categories`";
        ArrayList<String> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }
}
