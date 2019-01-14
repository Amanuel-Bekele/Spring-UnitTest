package com.emmanuel.test.controller;

import com.emmanuel.test.model.Item;
import com.emmanuel.test.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @PostMapping(value = "/item")
    public ResponseEntity<?> addItem(@Valid @RequestBody Item item, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            List<String> errorList = result.getFieldErrors().stream()
                    .map(fieldError -> "Field " + fieldError.getField() + "'" + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("error", errorList);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return itemService.addItem(item);


    }

}
