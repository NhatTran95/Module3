package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.Pageable;
import com.codegym.tour_manager.model.Tour;

import java.util.List;

public interface ITourService {
    List<Tour> findAll();

    void save(Tour tour);

    Tour findById(int id);

    void update(int id, Tour tour);

    void remove(int id);

    List<Tour> findTour(Pageable pageable);
}
