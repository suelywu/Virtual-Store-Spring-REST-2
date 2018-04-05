package com.example.virtualstore.application.service;

import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.domain.repository.ShoppingCartRepository;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.infrastructure.wrapper.ProductHolderWrapper;
import com.example.virtualstore.infrastructure.wrapper.ShoppingCartWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private ProductService productService;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public List<ProductHolderWrapper> getClientShoppingCartProdHolderWrappers(int clientId) {
        List<ProductHolder> productHolders = shoppingCartRepository.getShoppingCartProdHolders(clientId);
        List<ProductHolderWrapper> productHolderWrappers = new LinkedList<>();
        for (ProductHolder productHolder : productHolders) {
            ProductHolderWrapper productHolderWrapper = new ProductHolderWrapper(productHolder);
            productHolderWrappers.add(productHolderWrapper);
        }
        return productHolderWrappers;
    }

    public ShoppingCartWrapper getClientShoppingCartWrapper(int clientId) {
        return new ShoppingCartWrapper(shoppingCartRepository.findById(clientId));
    }

    public void addProductToClientShoppingCart(int clientId, int productId, int quantity) {
        Product product = productService.getProduct(productId).getProduct();
        //ProductHolder productHolder = new ProductHolder(product, quantity);
        shoppingCartRepository.addProductToShoppingCart(clientId, product, quantity);
        //shoppingCartRepository.addProductToShoppingCart(clientId, productHolder);
    }

    public boolean delProductFromClientShoppingCart(int clientId, int productId, int quantity) {
        Product product = productService.getProduct(productId).getProduct();
        //ProductHolder productHolder = new ProductHolder(product, quantity);
        return shoppingCartRepository.delProductFromShoppingCart(clientId, product, quantity);
        //shoppingCartRepository.delProductFromShoppingCart(clientId, productHolder);
    }

    public void addNewShoppingCart(int clientId){
        shoppingCartRepository.save(clientId);
    }

    public void clearClientShoppingCart(int clientId) {
        shoppingCartRepository.clearClientShoppingCart(clientId);
    }

}
