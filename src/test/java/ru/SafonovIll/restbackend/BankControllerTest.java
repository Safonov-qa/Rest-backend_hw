package ru.SafonovIll.restbackend;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ru.SafonovIll.restbackend.domain.UserInfo;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.with;

public class BankControllerTest {

    private RequestSpecification spec =
            with()
                    .baseUri("http://localhost:8080")
                    .basePath("/");

    @Test
    void bankControllerTest() {
        UserInfo[] userInfos = spec.get("user/getAll")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(UserInfo[].class);

        Stream.of(userInfos)
                .filter(userInfo -> userInfo.getUserName().equals("Ilya"))
                .peek(userInfo -> userInfo.getUserName().equals("Ilya"))
                .map(userInfo -> userInfo.toString())
                .collect(Collectors.toList());



    }
}
