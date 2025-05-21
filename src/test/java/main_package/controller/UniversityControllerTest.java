package main_package.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main_package.exception.UserNotFoundException;
import main_package.model.University;
import main_package.request.UniversityCreateRequest;
import main_package.service.UniversityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
    controllers = UniversityController.class,
    excludeAutoConfiguration = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class}
)
class UniversityControllerTest {
  private static final String basePath = "/api/university/user";
  private static final MediaType jsonContentType = MediaType.valueOf("application/json");

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private UniversityService universityService;


  @Test
  public void testGetUniversitySuccess() throws Exception {
    University universityData = new University(null, "МФТИ", "Долгопа");
    when(universityService.getUniversityById(1L))
        .thenReturn(universityData);

    mockMvc.perform(get(basePath + "/" + 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(jsonContentType))
        .andExpect(jsonPath("$.name").value(universityData.getUniversityName()))
        .andExpect(jsonPath("$.location").value(universityData.getLocation()));
    verify(universityService).getUniversityById(1L);
  }

  @Test
  public void testGetUniversityNotFound() throws Exception {
    when(universityService.getUniversityById(1000L))
        .thenThrow(UserNotFoundException.class);

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
