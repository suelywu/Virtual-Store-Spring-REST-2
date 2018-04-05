package com.example.virtualstore.ui.controller;

import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.infrastructure.wrapper.ProductWrapper;
import com.example.virtualstore.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductWrapper> getProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductWrapper getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String registerNewProduct(@RequestParam String name, @RequestParam double price) {
        productService.registerNewProduct(name, price);
        return "Product \"" + name + "\" with price \"" + price + "\" was registered successfully!";
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public String removeProductById(@PathVariable int id) {
        productService.removeById(id);
        return "Product with id \"" + id + "\" removed successfully!";
    }

    @RequestMapping(value = "/products", method = RequestMethod.DELETE)
    public String removeProduct(@RequestBody ProductWrapper productWrapper) {
        Product product = productWrapper.getProduct();
        int id = product.getId();
        productService.remove(product);
        return "Product with id \"" + id + "\" removed successfully!";
    }

}
