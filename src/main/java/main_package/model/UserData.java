package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserData", description = "Сущность пользователя")
public record UserData(String name, String surname) {

}
