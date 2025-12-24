package com.supplynest.cart_service.service.impl;

import com.supplynest.cart_service.dto.CartItemsDto;
import com.supplynest.cart_service.dto.CartsDto;
import com.supplynest.cart_service.entity.CartItems;
import com.supplynest.cart_service.entity.Carts;
import com.supplynest.cart_service.repository.CartItemsRepo;
import com.supplynest.cart_service.repository.CartRepo;
import com.supplynest.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final CartItemsRepo cartItemsRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<CartItemsDto> getCartDetails(Long customerId) {

        Carts cart = cartRepo.findByCustomerId(customerId)
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "Cart not found with id " + customerId
                        )
                );
        List<CartItems> cartItems = cartItemsRepo.findByCartId(cart.getId());

        if (cartItems.isEmpty()) {
            throw new ResponseStatusException(
                    NOT_FOUND,
                    "No items found for cart id " + cart.getId()
            );
        }
        return cartItems.stream()
                .map(item -> modelMapper.map(item, CartItemsDto.class))
                .toList();
    }

    @Override
    public CartsDto openNewCart(CartsDto cartsDto) {
        Carts newCart = modelMapper.map(cartsDto, Carts.class);
        Carts savedCart = cartRepo.save(newCart);
        return modelMapper.map(savedCart, CartsDto.class);
    }

    @Override
    public List<CartItemsDto> addItemToCart(Long productId, Double quantity) {

        //Checking the quantity is zero
        if (quantity == null || quantity <= 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantity must be greater than zero."
            );
        }
        return List.of();
    }
}
