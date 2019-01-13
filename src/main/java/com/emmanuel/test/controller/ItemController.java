package com.emmanuel.test.controller;

import com.emmanuel.test.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ItemController {


    @GetMapping(value = "/dummy-item")
    public Item getDummyItem() {
        return new Item(1, "Ball", 10, 100);
    }


}
