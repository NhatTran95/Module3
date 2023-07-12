package com.codegym.manager_products.service;

import com.codegym.manager_products.model.Category;
import com.codegym.manager_products.model.ESize;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ESizeServiceMysql implements IESizeService{
    private static final String FIND_ALL = "SELECT * FROM sizes";
    private static final String FIND_BY_ID = "SELECT * FROM sizes WHERE id = ?";
    private String jdbcURL = "jdbc:mysql://localhost:3306/manager_product?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "110130046";
    @Override
    public List<ESize> findAll() {
        List<ESize> sizes = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);

            System.out.println("findAll: "+ preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                ESize size = ESize.findById(id);


                sizes.add(size);
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return sizes;
    }

    @Override
    public ESize findById(int id) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1,id);

            System.out.println("findById: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int idSize = rs.getInt("id");
                String nameSize = rs.getString("name");
                ESize size = ESize.findById(id);
                return size;
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return null;
    }
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
