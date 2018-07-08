package com.mikolaj.nalecz.biblioteka.logic;

import com.mikolaj.nalecz.biblioteka.domain.Address;
import com.mikolaj.nalecz.biblioteka.domain.User;
import com.mikolaj.nalecz.biblioteka.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

@Service
public class UserLogic {

    private UserRepository userRepository;

    public UserLogic(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @SneakyThrows
    public void hibernateTest() {
        System.out.println("hibernateTest");
        User user = userRepository.findById(4L).orElse(null);
        Thread.sleep(1000);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void hibernateTest2() {
        System.out.println("hibernateTest2");
        User user = userRepository.findById(4L).orElse(null);
        user.addAddress(new Address("qwwewdgfer"));
    }

}
