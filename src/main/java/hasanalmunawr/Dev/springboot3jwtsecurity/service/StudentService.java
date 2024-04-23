package hasanalmunawr.Dev.springboot3jwtsecurity.service;

import hasanalmunawr.Dev.springboot3jwtsecurity.dto.StudentDto;
import hasanalmunawr.Dev.springboot3jwtsecurity.dto.UpdateStudentDto;
import hasanalmunawr.Dev.springboot3jwtsecurity.entity.AddressEntity;
import hasanalmunawr.Dev.springboot3jwtsecurity.entity.StudentEntity;
import hasanalmunawr.Dev.springboot3jwtsecurity.repository.AddresRepository;
import hasanalmunawr.Dev.springboot3jwtsecurity.repository.StudentReporitory;
import hasanalmunawr.Dev.springboot3jwtsecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j

public class StudentService {

    private final StudentReporitory studentReporitory;
    private final AddresRepository addressRepository;
    private final Validator validator;

    public StudentDto create(StudentDto studentDto) {
        log.info("[StudentService:create]: Creating student {}", studentDto.getFirstName());


        AddressEntity addressEntity = AddressEntity.builder()
                .street(studentDto.getAddress().getStreet())
                .city(studentDto.getAddress().getCity())
                .province(studentDto.getAddress().getProvince())
                .country(studentDto.getAddress().getCountry())
                .postalCode(studentDto.getAddress().getPostalCode())
                .build();
        addressRepository.save(addressEntity);

        StudentEntity studentEntity = StudentEntity.builder()
                .id(generateRandomId())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .major(studentDto.getMajor())
                .birthDate(convertStringToDate(studentDto.getBirthDate()))
                .address(addressEntity)
                .build();
        studentReporitory.save(studentEntity);
        log.info("[StudentService:create]: Created student {}", studentDto.getFirstName());


        return StudentDto.builder()
                .id(studentEntity.getId())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .major(studentDto.getMajor())
                .birthDate(studentDto.getBirthDate())
                .address(addressEntity)
                .build();
    }


    public StudentEntity read(String id) {
        log.info("[StudentService:read]: Reading student {}", id);
        StudentEntity studentEntity = studentReporitory.findById(id).orElse(null);
        return studentEntity;
    }

    public List<StudentEntity> readAll() {
        return studentReporitory.findAll();
    }

    @Transactional
    public StudentDto update(String id, UpdateStudentDto studentDto) {
        log.info("[StudentService:update]: Receive student id {}", id);
        StudentEntity studentEntity = studentReporitory.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Student Id not found"));

        log.info("[StudentService:update]: Updating student {}", studentEntity.getFirstName());
        studentEntity.setFirstName(studentDto.getFirstName());
        studentEntity.setLastName(studentDto.getLastName());
        studentEntity.setMajor(studentDto.getMajor());
        studentEntity.setBirthDate(convertStringToDate(studentDto.getBirthDate()));
        studentEntity.setAddress(studentDto.getAddress());
        studentReporitory.save(studentEntity);
        log.info("[StudentService:update]: Updated student {}", studentDto.getFirstName());

        return StudentDto.builder()
                .id(studentEntity.getId())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .major(studentEntity.getMajor())
                .birthDate(String.valueOf(studentEntity.getBirthDate()))
                .address(studentEntity.getAddress())
                .build();

    }

    @Transactional
    public String delete(String id) {
        log.info("[StudentService:delete]: Deleting student {}", id);

        studentReporitory.deleteById(id);
        return "Succes deleted Student Id : " + id;
    }


    private LocalDate convertStringToDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, format);
    }

    public  String generateRandomId() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Ensure the first digit is non-zero
        sb.append(random.nextInt(9) + 1);

        // Generate remaining 8 digits
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
