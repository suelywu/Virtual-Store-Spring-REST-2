package com.example.virtualstore.ui.controller;

import com.example.virtualstore.application.service.ShoppingCartService;
import com.example.virtualstore.application.service.finishOrder.FinishShoppingService;
import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.infrastructure.wrapper.ShoppingCartWrapper;
import com.example.virtualstore.application.service.ProductService;
import com.example.virtualstore.infrastructure.wrapper.payment.PaymentWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private ProductService productService;
    private FinishShoppingService finishShoppingService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService, FinishShoppingService finishShoppingService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.finishShoppingService = finishShoppingService;
    }

    @RequestMapping(value = "/clients/{clientId}/shoppingCart", method = RequestMethod.GET)
    public ShoppingCartWrapper getShoppingCartService(@PathVariable int clientId) {
        return shoppingCartService.getClientShoppingCartWrapper(clientId);
    }

    @RequestMapping(value = "/clients/{clientId}/shoppingCart", method = RequestMethod.PUT)
    public String addProductToShoppingCart(@PathVariable int clientId, @RequestParam int productId, @RequestParam int quantity) {
        Product product = productService.getProduct(productId).getProduct();
        if (productService.hasProduct(productId)) {
            shoppingCartService.addProductToClientShoppingCart(clientId, productId, quantity);
            return "Product \"" + product.getName() + "\" with id \"" + productId + "\" added to your shopping cart!";
        }
        return "Product with id \"" + productId + "\" doesn't exists!";
    }

    @RequestMapping(value = "/clients/{clientId}/shoppingCart", method = RequestMethod.DELETE)
    public String delProductFromShoppingCart(@PathVariable int clientId, @RequestParam int productId, @RequestParam int quantity) {
        Product product = productService.getProduct(productId).getProduct();
        if (productService.hasProduct(productId)) {
            if (shoppingCartService.delProductFromClientShoppingCart(clientId, productId, quantity)) {
                return "Product \"" + product.getName() + "\" with id \"" + productId + "\" was decreased successfully!";
            }
            return "Product \"" + product.getName() + "\" with id \"" + productId + "\" hasn't quantity enough to decrease!";
        }
        return "Product \"" + product.getName() + "\" with id \"" + productId + "\" doesn't exists!";
    }

    @RequestMapping(value = "/clients/{clientId}/shoppingCart/finish", method = RequestMethod.POST)
    public String finishShopping(@PathVariable int clientId, @RequestBody PaymentWrapper paymentWrapper) {
        return null;
    }

}
