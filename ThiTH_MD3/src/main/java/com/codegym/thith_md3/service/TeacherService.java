package com.codegym.thith_md3.service;

import com.codegym.thith_md3.model.Degree;
import com.codegym.thith_md3.model.EGender;
import com.codegym.thith_md3.model.Search;
import com.codegym.thith_md3.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeacherService extends DBContext implements ITeacherService{

    private static final String FIND_ALL_TEACHERS = "SELECT t.*, d.name as nameDegree FROM teachers t join degrees d ON t.idDegree = d.id";
    private static final String FIND_BY_ID = "SELECT t.*, d.name as nameDegree FROM teachers t join degrees d ON t.idDegree = d.id WHERE t.id = ?";

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            Connection connection  = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TEACHERS);

            System.out.println("findAll: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();        // khi trả về 1 hoặc nhiều dòng

            // rs.next(): kiểm tra coi có dòng tiếp theo ko
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                LocalDate date = rs.getDate("dateOfBirth").toLocalDate();
                String hobbie = rs.getString("hobbie");

                String gender = rs.getString("gender");
                EGender eGender = EGender.valueOf(gender);

                Teacher teacher = new Teacher(id, name, date, hobbie,eGender);
                long idDegree = rs.getLong("idDegree");
                String nameDegree = rs.getString("nameDegree");
                Degree degree = new Degree(idDegree, nameDegree);
                teacher.setDegree(degree);


                teachers.add(teacher);
            }
            connection.close();
        } catch (SQLException exception) {
            printSQLException(exception);
        }
        return teachers;
    }



    @Override
    public void save(Teacher teacher) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `teachers` (`name`, `dateOfBirth`, `hobbie`, `gender`, `idDegree`) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setString(1, teacher.getName());
            LocalDate sDate = teacher.getDateOfBirth();
            java.sql.Date Date = java.sql.Date.valueOf(sDate);
            preparedStatement.setDate(2, Date);
            preparedStatement.setString(3, teacher.getHobbie());
            preparedStatement.setString(4, teacher.getGender().toString());
            preparedStatement.setLong(5, teacher.getDegree().getId());

            System.out.println("save: " + preparedStatement);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException exception) {
            printSQLException(exception);
        }
    }

    @Override
    public Teacher findById(long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);

            preparedStatement.setLong(1, id);

            System.out.println("findById: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long idDB = rs.getLong("id");
                String name = rs.getString("name");
                LocalDate date = rs.getDate("dateOfBirth").toLocalDate();
                String hobbie = rs.getString("hobbie");

                String gender = rs.getString("gender");
                EGender eGender = EGender.valueOf(gender);

                Teacher teacher = new Teacher(idDB, name, date, hobbie,eGender);
                long idDegree = rs.getLong("idDegree");
                String nameDegree = rs.getString("nameDegree");
                Degree degree = new Degree(idDegree, nameDegree);
                teacher.setDegree(degree);

                return teacher;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(long id, Teacher teacher) {
        try{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `teachers` SET `name` = ?, `dateOfBirth` = ?, `hobbie` = ?, `gender` = ?, `idDegree` WHERE (`id` = ?)");
        preparedStatement.setString(1, teacher.getName());
        LocalDate sDate = teacher.getDateOfBirth();
        java.sql.Date Date = java.sql.Date.valueOf(sDate);
        preparedStatement.setDate(2, Date);
        preparedStatement.setString(3, teacher.getHobbie());
        preparedStatement.setString(4, teacher.getGender().toString());
        preparedStatement.setLong(5, teacher.getDegree().getId());
        preparedStatement.setLong(6, id);

        System.out.println("save: " + preparedStatement);
        preparedStatement.executeUpdate();

        connection.close();
    } catch (SQLException exception) {
        printSQLException(exception);
    }
    }

    @Override
    public void remove(long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `teachers` WHERE (`id` = ?);");
            preparedStatement.setLong(1,id);

            System.out.println("remove: " + preparedStatement);
            preparedStatement.executeUpdate();
            connection.close();

        }catch (SQLException exception){
            printSQLException(exception);
        }
    }

    @Override
    public List<Teacher> findTeacher(Search search) {
        return null;
    }
}
