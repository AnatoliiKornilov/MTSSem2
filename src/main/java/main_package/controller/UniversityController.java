package main_package.controller;

import java.util.List;
import java.util.stream.Collectors;
import main_package.model.UniversityData;
import main_package.request.UniversityCreateRequest;
import main_package.response.UniversityGetResponse;
import main_package.service.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/university")
public class UniversityController {

  private final UniversityService universityService;

  public UniversityController(UniversityService universityService) {
    this.universityService = universityService;
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<UniversityGetResponse> getUniversityById(@PathVariable Long userId) {
    UniversityData universityData = universityService.getUniversityById(userId);
    return ResponseEntity.status(HttpStatus.OK).body(new UniversityGetResponse(universityData.name(), universityData.location()));
  }

  @PutMapping("/user/{userId}")
  public ResponseEntity<Void> addUniversityForUserById(
      @PathVariable Long userId, @RequestBody UniversityCreateRequest university) {
    universityService.createUniversity(university);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
