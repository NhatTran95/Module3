package com.codegym.thith_md3.service;

import com.codegym.thith_md3.model.Degree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DegreeService extends DBContext implements IDegreeService{

    private static final String SELECT_ALL_DEGREE = "SELECT * FROM degrees";
    private static final String FIND_BY_ID = "SELECT * FROM degrees WHERE id = ?";

    @Override
    public List<Degree> findAll() {
        List<Degree> degrees = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEGREE);

            System.out.println("findAll: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long idDegree = rs.getLong("id");
                String name = rs.getString("name");
                Degree degree = new Degree(idDegree, name);

                degrees.add(degree);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return degrees;
    }

    @Override
    public Degree findById(long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);

            System.out.println("findById: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long idDegree = rs.getLong("id");
                String name = rs.getString("name");
                Degree degree = new Degree(idDegree, name);
                return degree;
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);

        }
        return null;
    }
}
