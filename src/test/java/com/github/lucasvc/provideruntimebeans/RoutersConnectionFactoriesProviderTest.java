package com.github.lucasvc.provideruntimebeans;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import com.github.lucasvc.provideruntimebeans.RoutersConnectionFactoriesProviderTest.Config;

@SpringBootTest(classes = { Config.class })
public class RoutersConnectionFactoriesProviderTest {

    @Test
    void shouldLoadContext() {
    }

    @Configuration
    @EnableAutoConfiguration
    static class Config {
    }

}
