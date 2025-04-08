package main_package.repository;

import lombok.RequiredArgsConstructor;
import main_package.exception.UniversityNotFoundException;
import main_package.model.University;
import main_package.model.UniversityData;
import org.springframework.stereotype.Repository;
import main_package.web.RestTemplateConfig;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
@RequiredArgsConstructor
public class InMemoryUniversityRepository implements UniversityRepository {

  public UniversityData university = new UniversityData("МФТИ", "Долгопа");
  private final RestTemplate restTemplate;
  private final WebClient webClient;

  @Override
  public UniversityData getUniversityById(Long id) {
    restTemplate.getForEntity("https://www.youtube.com/watch?v=xVRVVgBnYEE", String.class);
    if (id == 1) {
      return university;
    } else {
      throw new UniversityNotFoundException();
    }
  }

  @Override
  public Long createUniversity(UniversityData universityData) {
    University university = new University(78L, universityData);
    webClient
        .get()
        .uri("https://yandex.ru/video/preview/10054169858502721662")
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return 78L;
  }
}
