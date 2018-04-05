package com.example.virtualstore.ui.controller;

import com.example.virtualstore.infrastructure.wrapper.ProductHolderWrapper;
import com.example.virtualstore.application.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public List<ProductHolderWrapper> getStore() {
        return storeService.getStore();
    }

    @RequestMapping(value = "/store/{productId}", method = RequestMethod.GET)
    public ProductHolderWrapper getSpecificProduct(@PathVariable int productId) {
        return storeService.getSpecificProduct(productId);
    }

    @RequestMapping(value = "/store/has", method = RequestMethod.GET)
    public boolean hasEnough(@RequestParam int productId, @RequestParam int quantity) {
        return storeService.hasEnough(productId, quantity);
    }


}
