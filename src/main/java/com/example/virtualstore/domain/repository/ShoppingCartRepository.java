package com.example.virtualstore.domain.repository;

import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.domain.entity.ShoppingCart;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ShoppingCartRepository {

    private List<ShoppingCart> shoppingCarts;

    /*
    private ShoppingCart shoppingCart;

    public void setClientShoppingCart(int clientId) {
        this.shoppingCart = findById(clientId);
    }
    */

    public ShoppingCartRepository() {
        this.shoppingCarts = new LinkedList<>();
    }

    public ShoppingCart findById(int clientId) {
        return shoppingCarts.stream().filter(shoppingCart -> shoppingCart.equals(clientId)).findFirst().orElse(new ShoppingCart(0));
    }

    public void addProductToShoppingCart(int clientId, ProductHolder productHolder) {
        ShoppingCart shoppingCart = findById(clientId);
        shoppingCart.addProduct(productHolder);
    }

    public void addProductToShoppingCart(int clientId, Product product, int quantity) {
        ShoppingCart shoppingCart = findById(clientId);
        shoppingCart.addProduct(product, quantity);
    }

    public boolean delProductFromShoppingCart(int clientId, ProductHolder productHolder) {
        ShoppingCart shoppingCart = findById(clientId);
        return shoppingCart.delProduct(productHolder);
    }

    public boolean delProductFromShoppingCart(int clientId, Product product, int quantity) {
        ShoppingCart shoppingCart = findById(clientId);
        return shoppingCart.delProduct(product.getId(), quantity);
    }

    public List<ProductHolder> getShoppingCartProdHolders(int clientId) {
        ShoppingCart shoppingCart = findById(clientId);
        return shoppingCart.getProductHolders();
    }

    public void clearClientShoppingCart(int clientId) {
        ShoppingCart shoppingCart = findById(clientId);
        shoppingCart.clear();
    }

    public void save(int clientId) {
        ShoppingCart shoppingCart = new ShoppingCart(clientId);
        shoppingCarts.add(shoppingCart);
    }

}
