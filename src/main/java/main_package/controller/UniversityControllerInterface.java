package main_package.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import main_package.exception.UniversityNotFoundException;
import main_package.request.UniversityCreateRequest;
import main_package.response.UniversityGetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "University API", description = "Управление университетами")
public interface UniversityControllerInterface {

  @Operation(summary = "Получить университет пользователя по его id")
  @ApiResponse(responseCode = "200", description = "Университет пользователя получен")
  @ApiResponse(responseCode = "404", description = "Университет не найден",
      content = @Content(schema = @Schema(implementation = UniversityNotFoundException.class)))
  @GetMapping("/user/{userId}")
  ResponseEntity<UniversityGetResponse> getUniversityById(@Parameter(description = "ID пользователя")
  @PathVariable Long userId);

  @Operation(summary = "Добавить университет пользователю")
  @ApiResponse(responseCode = "201", description = "Университет добавлен пользователю")
  @PutMapping("/user/{userId}")
  ResponseEntity<Void> addUniversityForUserById(@Parameter(description = "ID пользователя")
  @PathVariable Long userId, @RequestBody UniversityCreateRequest university);
}
