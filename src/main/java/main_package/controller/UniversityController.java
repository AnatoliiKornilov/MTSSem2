package main_package.controller;

import main_package.model.UniversityData;
import main_package.request.UniversityCreateRequest;
import main_package.response.UniversityGetResponse;
import main_package.service.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/university")
public class UniversityController implements UniversityControllerInterface {

  private final UniversityService universityService;

  public UniversityController(UniversityService universityService) {
    this.universityService = universityService;
  }

  @Override
  public ResponseEntity<UniversityGetResponse> getUniversityById(Long userId) {
    UniversityData universityData = universityService.getUniversityById(userId);
    return ResponseEntity.status(HttpStatus.OK).body(new UniversityGetResponse(universityData.name(), universityData.location()));
  }

  @Override
  public ResponseEntity<Void> addUniversityForUserById(Long userId, UniversityCreateRequest university) {
    universityService.createUniversity(university);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
