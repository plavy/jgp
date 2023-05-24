package com.example.jgp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jgp.repository.StanicaRepository;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class StanicaRepositoryTest {
    
    @Autowired
    StanicaRepository stanicaRepository;

    @Test
    public void repositoryConnected() {
        assertThat(stanicaRepository).isNotNull();
    }
}
