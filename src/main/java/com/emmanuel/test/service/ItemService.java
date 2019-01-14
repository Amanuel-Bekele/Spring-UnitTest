package com.emmanuel.test.service;

import com.emmanuel.test.model.Item;
import com.emmanuel.test.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public Item getDummyItem() {
        return new Item(10, "Ball", 10, 100);
    }

    public List<Item> getItemsList() {

        List<Item> items = repository.findAll();

        for (Item item : items)
            item.setValue(item.getPrice() * item.getQuantity());

        return items;
    }

    public ResponseEntity<?> addItem(Item item) {

        Map<String, Object> response = new HashMap<>();

        Item itemSaved;

        try {
            itemSaved = repository.save(item);
        } catch (DataAccessException ex) {
            response.put("Message: ", "Error Adding to the Database");
            response.put("error", Objects.requireNonNull(ex.getMessage()).concat(": ")
                    .concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("Message", "Item has been created successfully");
        response.put("Item", itemSaved);

        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }
}
