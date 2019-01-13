package com.emmanuel.test.controller;

import com.emmanuel.test.model.Item;
import com.emmanuel.test.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemControllerWithService {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "dummyItem")
    public Item getItem(){
        return itemService.getDummyItem();
    }



    @GetMapping(value = "/item")
    public List<Item> getAllItems(){
        return itemService.getItemsList();
    }
}
