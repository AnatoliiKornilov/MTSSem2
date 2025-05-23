package main_package.controller.actuator;

import java.util.UUID;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "uuid")
public class RandomUUIDController {

  @ReadOperation
  public UUID customInfo() {
    return UUID.randomUUID();
  }
}
