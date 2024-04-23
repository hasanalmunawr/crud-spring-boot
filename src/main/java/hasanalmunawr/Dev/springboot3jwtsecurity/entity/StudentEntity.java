package hasanalmunawr.Dev.springboot3jwtsecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "students")
public class StudentEntity {

    @Id
    @Column(unique = true, nullable = false)
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String major;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;


}
