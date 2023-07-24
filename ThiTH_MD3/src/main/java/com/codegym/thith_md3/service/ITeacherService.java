package com.codegym.thith_md3.service;

import com.codegym.thith_md3.model.Search;
import com.codegym.thith_md3.model.Teacher;

import java.util.List;

public interface ITeacherService {
    List<Teacher> findAll();

    void save(Teacher teacher);

    Teacher findById(long id);

    void update(long id, Teacher teacher);

    void remove(long id);

    List<Teacher> findTeacher(Search search);
}
