package hasanalmunawr.Dev.springboot3jwtsecurity.dto;

import hasanalmunawr.Dev.springboot3jwtsecurity.entity.AddressEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {


    private String id;
    @NotBlank(message = "First Name Must Not Blank")
    private String firstName;
    private String lastName;

    @NotBlank(message = "Birsth Date Must Not Blank")
    private String birthDate;

    @NotBlank(message = "Major Must Not Blank")
    private String major;

    private AddressEntity address;




}
