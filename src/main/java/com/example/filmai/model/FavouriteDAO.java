package com.example.filmai.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavouriteDAO {

    public static void create(Favourite favourite) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "INSERT INTO `favourites` (`book_id`) VALUES (?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, favourite.getUserId());
            preparedStatement.setInt(2, favourite.getBookId());
            preparedStatement.setString(3, favourite.getBookName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko ištrinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Įrašo ištrinti nepavyko");
        }
    }

    public static List<Favourite> searchListByUserId(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "";
        querry = "SELECT * FROM `categories`";
        ArrayList<Favourite> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //Kol turime sarase elementus
                list.add(new Favourite(
                        resultSet.getInt("fav_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getString("book_name")
                ));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }
}
