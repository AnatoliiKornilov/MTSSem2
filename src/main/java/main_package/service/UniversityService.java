package main_package.service;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import main_package.model.UniversityData;
import main_package.repository.UniversityRepository;
import main_package.request.UniversityCreateRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UniversityService {

  private final UniversityRepository universityRepository;

  public UniversityService(UniversityRepository universityRepository) {
    this.universityRepository = universityRepository;
  }

  public Long createUniversity(UniversityCreateRequest request) {
    log.info("Adding new university {}", request.name(), request.location());
    Long universityId = universityRepository.createUniversity(new UniversityData(request.name(), request.location()));
    log.info("Created new university with id: {}", universityId);
    return universityId;
  }

  public UniversityData getUniversityById(Long userId) {
    log.info("Getting university by id: {}", userId);
    UniversityData university = universityRepository.getUniversityById(userId);
    log.info("Found university: {}", university.name());
    return university;
  }
}
