package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.Pageable;
import com.codegym.tour_manager.model.Tour;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TourServiceMyspl implements ITourService{
    private static final String FIND_ALL_TOURS = "SELECT * FROM tours";
    private static final String SAVE_TOUR = "INSERT INTO `tours` (`destination`, `route`, `startDate`, `endDate`, `price`, `img`) VALUES (?, ?, ?, ?, ?, ?);\n";
    private static final String FIND_BY_ID = "SELECT * FROM tours t WHERE t.id = ?";
    private static final String UPDATE_TOUR = "UPDATE `tours` SET `destination` = ?, `route` = ?, `startDate` = ?, `endDate` = ?, `price` = ?, `img` = ? WHERE (`id` = ?)";
    private static final String REMOVE_TOUR = "DELETE FROM `tours` WHERE (`id` = ?)";

    private String jdbcURL = "jdbc:mysql://localhost:3306/tour_manager?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "110130046";
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
    @Override
    public List<Tour> findAll() {
        List<Tour> tours = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TOURS);
            System.out.println("findAll: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String destination = rs.getString("destination");
                String route = rs.getString("route");
                java.sql.Date sDate = rs.getDate("startDate");
                LocalDate startDate = sDate.toLocalDate();
                java.sql.Date eDate = rs.getDate("endDate");
                LocalDate endDate = eDate.toLocalDate();
                float price = rs.getFloat("price");
                String img = rs.getString("img");
                Tour tour = new Tour(id, destination,route, startDate, endDate, price, img);
                tours.add(tour);
             }
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
        return tours;
    }

    @Override
    public void save(Tour tour) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_TOUR);
            preparedStatement.setString(1, tour.getDestination());
            preparedStatement.setString(2, tour.getRoute());
            LocalDate sDate = tour.getStartDate();
            java.sql.Date startDate = java.sql.Date.valueOf(sDate);
            preparedStatement.setDate(3, startDate);
            LocalDate eDate = tour.getEndDate();
            java.sql.Date endtDate = java.sql.Date.valueOf(eDate);
            preparedStatement.setDate(4, endtDate);
            preparedStatement.setFloat(5, tour.getPrice());
            preparedStatement.setString(6, tour.getImg());
            System.out.println("save: " + preparedStatement);
            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public Tour findById(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println("findById: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String destination = rs.getString("destination");
                String route = rs.getString("route");
                java.sql.Date sDate = rs.getDate("startDate");
                LocalDate startDate = sDate.toLocalDate();
                java.sql.Date eDate = rs.getDate("endDate");
                LocalDate endDate = eDate.toLocalDate();
                float price = rs.getFloat("price");
                String img = rs.getString("img");
                Tour tour = new Tour(id, destination,route, startDate, endDate, price, img);
                return tour;
            }

        }catch (SQLException e){
            printSQLException(e);
        }
        return null;
    }

    @Override
    public void update(int id, Tour tour) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOUR);
            preparedStatement.setString(1, tour.getDestination());
            preparedStatement.setString(2, tour.getRoute());
            LocalDate sDate = tour.getStartDate();
            java.sql.Date startDate = java.sql.Date.valueOf(sDate);
            preparedStatement.setDate(3, startDate);
            LocalDate eDate = tour.getEndDate();
            java.sql.Date endtDate = java.sql.Date.valueOf(eDate);
            preparedStatement.setDate(4, endtDate);
            preparedStatement.setFloat(5, tour.getPrice());
            preparedStatement.setString(6, tour.getImg());
            preparedStatement.setInt(7, id);
            System.out.println("update: " + preparedStatement);
            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public void remove(int id) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_TOUR);
            preparedStatement.setInt(1, id);
            System.out.println("remove" + preparedStatement);
            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public  List<Tour> findTour(Pageable pageable) {
        List<Tour> tours = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tours t WHERE t.destination like ? limit ?,?;");
            preparedStatement.setString(1, "%" + pageable.getKw() + "%");
            preparedStatement.setInt(2, (pageable.getPage()-1)*pageable.getLimit());
            preparedStatement.setInt(3, pageable.getLimit());

            System.out.println("findTour:" + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String destination = rs.getString("destination");
                String route = rs.getString("route");
                java.sql.Date sDate = rs.getDate("startDate");
                LocalDate startDate = sDate.toLocalDate();
                java.sql.Date eDate = rs.getDate("endDate");
                LocalDate endDate = eDate.toLocalDate();
                float price = rs.getFloat("price");
                String img = rs.getString("img");
                Tour tour = new Tour(id, destination,route, startDate, endDate, price, img);
                tours.add(tour);
            }

            preparedStatement = connection.prepareStatement("SELECT COUNT(*) as total FROM tours t WHERE t.destination like ?");
            preparedStatement.setString(1, "%" + pageable.getKw() + "%");
            System.out.println("findCount:" +rs);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                int total = rs.getInt("total");
                pageable.setTotal((int) Math.ceil((total*1.0)/ pageable.getLimit()));
            }
        }catch (SQLException e){
            printSQLException(e);
        }


        return tours;
    }

}
