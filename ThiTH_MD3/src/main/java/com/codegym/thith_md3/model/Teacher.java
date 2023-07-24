package com.codegym.thith_md3.model;

import java.time.LocalDate;

public class Teacher {
    private long id;
    private String name;
    private LocalDate dateOfBirth;
    private String hobbie;
    private EGender gender;
    private long idDegree;
    private Degree degree;

    public Teacher(long id, String name, LocalDate dateOfBirth, String hobbie, EGender gender) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.hobbie = hobbie;
        this.gender = gender;
    }

    public Teacher() {
    }

    public Teacher(long id, String name, LocalDate dateOfBirth, String hobbie, EGender gender, long idDegree) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.hobbie = hobbie;
        this.gender = gender;
        this.idDegree = idDegree;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHobbie() {
        return hobbie;
    }

    public void setHobbie(String hobbie) {
        this.hobbie = hobbie;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public long getIdDegree() {
        return idDegree;
    }

    public void setIdDegree(long idDegree) {
        this.idDegree = idDegree;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }
}
