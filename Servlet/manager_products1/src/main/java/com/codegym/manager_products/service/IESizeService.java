package com.codegym.manager_products.service;

import com.codegym.manager_products.model.Category;
import com.codegym.manager_products.model.ESize;

import java.util.List;

public interface IESizeService {
    List<ESize> findAll();
    ESize findById(int id);
}
