package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.ERole;
import com.codegym.tour_manager.model.User;
import com.codegym.tour_manager.utils.PasswordUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService{
    private static final String SQL_FIND_USERNAME = "SELECT * FROM users WHERE `userName` like ?";
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
    public User findUserByUserName(String username) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_USERNAME);
            ps.setString(1, "%" + username + "%");
            System.out.println("findUserByUserName: " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idDB = rs.getInt("id");
                String usernameDB = rs.getString("userName");
                String passwordDB = rs.getString("passWord");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String role = rs.getString("role");
                ERole eRole = ERole.valueOf(role);
//                return new User(usernameDB, passwordDB, eRole);
                return new User(idDB, usernameDB, passwordDB, fullname, email, phone, eRole);
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        try {
            Connection connection = getConnection();

            // mã hóa
            String strPass = PasswordUtils.hashPassword(user.getPassword());
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `users` (`userName`, `passWord`, `role`) VALUES (?, ?, ?)");


            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, strPass);
            preparedStatement.setString(3, user.getRole().toString());


            System.out.println("createUser: " + preparedStatement);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("SELECT LAST_INSERT_ID() as last_id;");
            System.out.println("createUser: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int lastId = rs.getInt("last_id");
                user.setId(lastId);
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return user;
    }

    @Override
    public void updateUser(int id, User user) {
        try{
            Connection connection =getConnection();
//
            PreparedStatement pr = connection.prepareStatement("UPDATE `users` SET `fullname` = ?, `email` = ?, `phone` = ? WHERE `id` = ?");
            pr.setString(1, user.getFullname());
            pr.setString(2, user.getEmail());
            pr.setString(3, user.getPhone());
            pr.setInt(4, id);
            System.out.println("update: " + pr);
            pr.executeUpdate();
            connection.close();

        }catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public void updatePassword(int id, User user){
        try{
            Connection connection = getConnection();
            String strPass = PasswordUtils.hashPassword(user.getPassword());
            PreparedStatement pr = connection.prepareStatement("UPDATE `users` SET `passWord` = ? WHERE `id` = ?");
            pr.setString(1, strPass);
            pr.setInt(2, id);
            System.out.println("updatePassword: " + pr);
            pr.executeUpdate();
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
    }
    @Override
    public List<User> findRoleUser(){
        List<User> users = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement pr = connection.prepareStatement("SELECT * FROM users WHERE role = 'USER'");
            System.out.println("findRoleUser" + pr);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                User user = new User(fullname,email,phone);
                users.add(user);
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return users;

    }
    public User findUserById(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE `id` like ?");
            ps.setInt(1, id );
            System.out.println("findUserById: " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                int idDB = rs.getInt("id");
                String usernameDB = rs.getString("userName");
                String passwordDB = rs.getString("passWord");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String role = rs.getString("role");
                ERole eRole = ERole.valueOf(role);
//                return new User(usernameDB, passwordDB, eRole);
                return new User(id, usernameDB, passwordDB, fullname, email, phone, eRole);
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }
}
