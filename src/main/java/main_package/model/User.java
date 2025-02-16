package main_package.model;

import java.util.ArrayList;

public record User(Long id, UserData fullName, ArrayList<BookData> books, UniversityData university, ArrayList<CourseData> courses) {

}
