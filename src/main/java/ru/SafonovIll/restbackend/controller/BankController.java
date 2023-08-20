package ru.SafonovIll.restbackend.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.SafonovIll.restbackend.domain.LoginInfo;
import ru.SafonovIll.restbackend.domain.UserInfo;
import ru.SafonovIll.restbackend.exception.InvalidUsernameException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BankController {

    private Map<String, UserInfo> users = Map.of(
            "Ilya", UserInfo.builder().userName("Ilya").build(),
            "Ivan", UserInfo.builder().userName("Ivan").build(),
            "Igor", UserInfo.builder().userName("Igor").build()
    );

    @PostMapping("user/login")
    @ApiOperation("Авторизация")
    public UserInfo doLogin(@RequestBody LoginInfo loginInfo) throws InvalidUsernameException {
        if (loginInfo.getUserName().equals("Ilya")) {
            return UserInfo.builder()
                    .loginDate(new Date())
                    .userName(loginInfo.getUserName())
                    .build();
        } else {
            throw new InvalidUsernameException();
        }
    }

    @GetMapping("user/getAll")
    @ApiOperation("Получение всех юзеров")
    public List<UserInfo> getAllUsersInfo() {
        return users.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

    }
}
