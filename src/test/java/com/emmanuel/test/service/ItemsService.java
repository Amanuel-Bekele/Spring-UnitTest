package com.emmanuel.test.service;

import com.emmanuel.test.model.Item;
import com.emmanuel.test.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class ItemsService {

    @Mock
    private ItemRepository repository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void itemControllerTest_getAllItems(){
        List<Item> mockItemsList = Arrays.asList(new Item(54, "Item1", 21, 12),
                new Item(98, "Item2", 24, 24));

        int wantedNumberOfInvocations_repository_findAll = 2;
        Mockito.when(repository.findAll()).thenReturn(mockItemsList);

        Assert.assertEquals(mockItemsList.size(), itemService.getItemsList().size());
        Assert.assertEquals("Item2", itemService.getItemsList().get(1).getName());
        Mockito.verify(repository, Mockito.times(wantedNumberOfInvocations_repository_findAll)).findAll();

    }
}
