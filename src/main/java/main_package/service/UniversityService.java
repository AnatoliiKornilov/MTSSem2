package main_package.service;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import main_package.exception.UniversityNotFoundException;
import main_package.model.University;
import main_package.model.UniversityData;
import main_package.repository.UniversityRepository;
import main_package.request.UniversityCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UniversityService {

  private final UniversityRepository universityRepository;

  public UniversityService(UniversityRepository universityRepository) {
    this.universityRepository = universityRepository;
  }

  @Transactional
  public Long createUniversity(UniversityCreateRequest request) {
    log.info("Adding new university {} {}", request.name(), request.location());
    University university = universityRepository.save(new University(null, new UniversityData(request.name(), request.location())));
    log.info("Created new university");
    return university.getId();
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  public University getUniversityById(Long userId) {
    log.info("Getting university by id: {}", userId);
    University university = universityRepository.findById(userId).orElseThrow(
        UniversityNotFoundException::new);
    log.info("Found university: {}", university.getUniversityData().name());
    return university;
  }
}
