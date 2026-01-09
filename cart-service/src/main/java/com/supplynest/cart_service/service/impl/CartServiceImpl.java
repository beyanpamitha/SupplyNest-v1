package com.supplynest.cart_service.service.impl;

import com.supplynest.cart_service.dto.CartItemsDto;
import com.supplynest.cart_service.dto.CartsDto;
import com.supplynest.cart_service.dto.ProductPriceDto;
import com.supplynest.cart_service.entity.CartItems;
import com.supplynest.cart_service.entity.Carts;
import com.supplynest.cart_service.repository.CartItemsRepo;
import com.supplynest.cart_service.repository.CartRepo;
import com.supplynest.cart_service.service.CartService;
import com.supplynest.cart_service.service.CatalogClient;
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
    private final CatalogClient catalogClient;

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
    public CartsDto openNewCart(Long customerId) {
        Carts cart = new Carts();
        cart.setCustomerId(customerId);

        Carts savedCart = cartRepo.save(cart);

        return modelMapper.map(savedCart, CartsDto.class);
    }

    @Override
    public void addItemToCart(Long customerId, Long productId, Double quantity) {

        //Fetch existing cart
        Carts cart = cartRepo.findByCustomerId(customerId)
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "Cart not found for customer"
                        )
                );

        //Fetch product price from Catalog
        ProductPriceDto product = catalogClient.getItemUnitPrice(productId);

        //Checking the active status
        if (product == null || !Boolean.TRUE.equals(product.getActiveStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product is not available"
            );
        }

        //Check if item already exists in cart
        CartItems existingItem = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            //Increase quantity
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            //Add new item
            CartItems item = new CartItems();
            item.setCart(cart);
            item.setProductId(productId);
            item.setQuantity(quantity);
            item.setUnitPrice(product.getUnitPrice());
            cart.getItems().add(item);
        }

        //Save cart
        cartRepo.save(cart);
    }

    @Override
    public void deleteItemFromCart(Long customerId, Long productId) {
        Carts cart = cartRepo.findByCustomerId(customerId)
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "Cart not found for customer"
                        )
                );

        CartItems item = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Item not found in cart"
                ));

        cart.getItems().remove(item);
        cartRepo.save(cart);
    }

    @Override
    public void updateItemsQuantity(Long customerId, Long productId, Double quantity) {

        //Checking if quantity is 0
        if (quantity < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantity cannot be negative"
            );
        }

        //Finding the cart b customerId
        Carts cart = cartRepo.findByCustomerId(customerId)
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "Cart not found for customer"
                        )
                );

        //Finding the item by productId
        CartItems item = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Item not found in cart"
                ));

        //Updating the quantity(Remove item if quantity = 0)
        if (quantity == 0){
            cart.getItems().remove(item);
        }else {
            item.setQuantity(quantity);
        }

        cartRepo.save(cart);
    }

}
