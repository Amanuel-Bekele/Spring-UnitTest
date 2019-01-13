package com.emmanuel.test.service;

import com.emmanuel.test.model.Item;
import com.emmanuel.test.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public Item getDummyItem() {
        return new Item(10, "Ball", 10, 100);
    }

    public List<Item> getItemsList(){

        List<Item> items =  repository.findAll();

        for (Item item : items)
            item.setValue(item.getPrice() * item.getQuantity());

        return items;
    }
}
