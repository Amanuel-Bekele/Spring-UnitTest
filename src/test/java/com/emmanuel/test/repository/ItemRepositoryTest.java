package com.emmanuel.test.repository;

import com.emmanuel.test.model.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJdbcTest // <-- Launches the in memory database makes the query
// on real applications write some data.sql source and put it in test folder - just for test purpose.
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;

    @Test
    public void itemRepositoryTest_findAll(){
        List<Item> itemList = repository.findAll();
        Assert.assertEquals(4, itemList.size());

        Assert.assertNotNull(repository);
    }
}
