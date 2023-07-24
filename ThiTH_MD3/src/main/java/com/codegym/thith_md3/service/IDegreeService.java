package com.codegym.thith_md3.service;

import com.codegym.thith_md3.model.Degree;

import java.util.List;

public interface IDegreeService {
    List<Degree> findAll();
    Degree findById(long id);
}
