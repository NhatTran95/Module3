package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.CartItem;

import java.util.List;

public interface ICartItemService {
    List<CartItem> getAllCartItems(int idCart);
    CartItem saveCartItem(CartItem cartItem);
    CartItem updateCartItem(CartItem cartItem);

    void deleteCartItem(int idCartItem);
    CartItem findCartItemById(int cartId, int idTour);
}
