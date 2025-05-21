package main_package.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RateLimiter;
import main_package.model.University;
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
  private final CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("CourseControllerCircuitBreaker");
  private final RateLimiter rateLimiter = RateLimiter.ofDefaults("CourseControllerRateLimiter");

  public UniversityController(UniversityService universityService) {
    this.universityService = universityService;
  }

  @Override
  public ResponseEntity<UniversityGetResponse> getUniversityById(Long userId) {
    return circuitBreaker.executeSupplier(() -> rateLimiter.executeSupplier(() -> {
    University universityData = universityService.getUniversityById(userId);
    return ResponseEntity.status(HttpStatus.OK).body(new UniversityGetResponse(universityData.getUniversityName(), universityData.getLocation()));
    }));
  }

  @Override
  public ResponseEntity<Void> addUniversityForUserById(Long userId, UniversityCreateRequest university) {
    return circuitBreaker.executeSupplier(() -> rateLimiter.executeSupplier(() -> {
      universityService.createUniversity(university);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }));
  }
}
