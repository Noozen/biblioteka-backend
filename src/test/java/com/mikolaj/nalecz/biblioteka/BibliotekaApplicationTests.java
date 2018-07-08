package com.mikolaj.nalecz.biblioteka;

import com.mikolaj.nalecz.biblioteka.logic.UserLogic;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BibliotekaApplicationTests {

    @Autowired
    private UserLogic userLogic;

    @Test
    @SneakyThrows
    public void contextLoads() {
        CompletableFuture.runAsync(userLogic::hibernateTest);
        CompletableFuture.runAsync(userLogic::hibernateTest2);
        System.out.println("Main");
        Thread.sleep(2000);
    }

}
