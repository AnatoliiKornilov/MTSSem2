package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;

@Schema(name = "User", description = "Сущность пользователя с id")
public record User(Long id, UserData fullName, ArrayList<BookData> books, UniversityData university, ArrayList<CourseData> courses) {

}
