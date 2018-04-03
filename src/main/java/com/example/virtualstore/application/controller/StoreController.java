package com.example.virtualstore.application.controller;

import com.example.virtualstore.infrastructure.wrapper.ProductHolderWrapper;
import com.example.virtualstore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
