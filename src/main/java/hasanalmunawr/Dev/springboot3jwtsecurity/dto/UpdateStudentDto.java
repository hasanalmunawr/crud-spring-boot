package hasanalmunawr.Dev.springboot3jwtsecurity.dto;

import hasanalmunawr.Dev.springboot3jwtsecurity.entity.AddressEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateStudentDto {

    private String firstName;
    private String lastName;
    private String birthDate;
    private String major;
    private AddressEntity address;
}
