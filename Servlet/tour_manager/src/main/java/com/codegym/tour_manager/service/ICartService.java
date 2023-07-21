package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.Cart;

public interface ICartService {
    Cart getCartById(int idUser);
    Cart createCart(Cart cart);

    Cart updateCart(Cart cart);

    void addToCart(int idTour, int quantity, int id);

    Cart updateCartInfo(int id, int idTour, int quantity);

    void remove(int id);
}
