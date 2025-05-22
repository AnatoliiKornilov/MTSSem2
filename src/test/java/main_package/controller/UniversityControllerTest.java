package main_package.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import main_package.Application;
import main_package.exception.UniversityNotFoundException;
import main_package.exception.UserNotFoundException;
import main_package.model.University;
import main_package.repository.UniversityRepository;
import main_package.request.UniversityCreateRequest;
import main_package.security.SecurityConfig;
import main_package.service.UniversityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UniversityController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(value = {UniversityController.class, ExceptionController.class})
@Testcontainers
@ActiveProfiles("test")
@ContextConfiguration(classes = {Application.class, SecurityConfig.class})
class UniversityControllerTest {
  private static final String basePath = "/api/university/user";
  private static final MediaType jsonContentType = MediaType.valueOf("application/json");


  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private UniversityService universityService;

  @MockitoBean
  private CircuitBreakerRegistry circuitBreakerRegistry;

  @MockitoBean
  private RateLimiterRegistry rateLimiterRegistry;
//
//  @BeforeEach
//  void setup() {
//    CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("test");
//    RateLimiter rateLimiter = RateLimiter.ofDefaults("test");
//    when(circuitBreakerRegistry.circuitBreaker(any())).thenReturn(circuitBreaker);
//    when(rateLimiterRegistry.rateLimiter(any())).thenReturn(rateLimiter);
//  }

  @Test
  public void testGetUniversitySuccess() throws Exception {
    University testUniversity = new University("MIPT", "Dolgopa");
    when(universityService.getUniversityById(any(Long.class)))
        .thenReturn(testUniversity);

    mockMvc.perform(get(basePath + "/" + 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(jsonContentType))
        .andExpect(jsonPath("$.location").value(testUniversity.getLocation()));
    verify(universityService).getUniversityById(1L);
  }

  @Test
  public void testGetUniversityNotFound() throws Exception {
    when(universityService.getUniversityById(any(Long.class)))
        .thenThrow(UniversityNotFoundException.class);

    mockMvc.perform(get(basePath + "/" + 1000))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testCreateUniversity() throws Exception {
    UniversityCreateRequest createRequest = new UniversityCreateRequest("МФТИ", "Долгопа");

    mockMvc.perform(
            post(basePath)
                .contentType(jsonContentType)
                .content(objectMapper.writeValueAsString(createRequest)));
  }
}
