package org.example.users;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // This will use application-test.yml
class UsersApplicationTests {

    @BeforeAll
    static void setup() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();


        System.setProperty("POSTGRES_HOST", dotenv.get("POSTGRES_HOST", "localhost"));
        System.setProperty("POSTGRES_PORT", dotenv.get("POSTGRES_PORT", "5432"));
        System.setProperty("POSTGRES_DB", dotenv.get("POSTGRES_DB", "testdb"));
        System.setProperty("POSTGRES_USER", dotenv.get("POSTGRES_USER", "sa"));
        System.setProperty("POSTGRES_PASSWORD", dotenv.get("POSTGRES_PASSWORD", ""));
    }

    @Test
    void contextLoads() {
    }
}