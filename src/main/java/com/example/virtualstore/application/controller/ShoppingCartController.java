package com.example.virtualstore.application.controller;

import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.domain.entity.ShoppingCart;
import com.example.virtualstore.infrastructure.wrapper.ShoppingCartWrapper;
import com.example.virtualstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {

    private ShoppingCart shoppingCart;
    private ProductService productService;

    @Autowired
    public ShoppingCartController(ShoppingCart shoppingCart, ProductService productService) {
        this.shoppingCart = shoppingCart;
        this.productService = productService;
    }

    @RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
    public ShoppingCartWrapper getShoppingCart() {
        return new ShoppingCartWrapper(shoppingCart);
    }

    @RequestMapping(value = "/shoppingCart/add", method = RequestMethod.PUT)
    public String addProductToShoppingCart(@RequestParam int productId,@RequestParam int quantity) {
        Product product = productService.getProduct(productId).getProduct();
        if (product.getId() != 0) {
            shoppingCart.addProduct(product, quantity);
            return "Product \"" + product.getName() + "\" with id \"" + productId + "\" added to your shopping cart!";
        }
        return "Product with id \"" + productId + "\" doesn't exists!";
    }

    @RequestMapping(value = "/shoppingCart/decrease", method = RequestMethod.DELETE)
    public String decreaseProductFromShoppingCart(@RequestParam int productId, @RequestParam int quantity) {
        Product product = productService.getProduct(productId).getProduct();
        if (product.getId() != 0) {
            if (shoppingCart.delProduct(productId, quantity)) {
                return "Product with id \"" + productId + "\" was decreased successfully!";
            }
            return "Product with id \"" + productId + "\" hasn't quantity enough to decrease!";
        }
        return "Product with id \"" + productId + "\" doesn't exists!";
    }


}
