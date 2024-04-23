package hasanalmunawr.Dev.springboot3jwtsecurity.controller;

import hasanalmunawr.Dev.springboot3jwtsecurity.dto.StudentDto;
import hasanalmunawr.Dev.springboot3jwtsecurity.dto.UpdateStudentDto;
import hasanalmunawr.Dev.springboot3jwtsecurity.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<?> create(@RequestBody StudentDto studentDto) {
        return ResponseEntity.accepted().body(studentService.create(studentDto));
    }

    @GetMapping(path = "/students")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<?> get(@RequestParam(name = "id", required = true) String studentId) {
        return ResponseEntity.ok(studentService.read(studentId));
    }

    @GetMapping("/all-students")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(studentService.readAll());
    }

    @PutMapping("/students")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> put(@RequestParam(name = "id") String id,
                                 @RequestBody UpdateStudentDto studentDto) {
        return ResponseEntity.ok(studentService.update(id, studentDto));
    }

    @DeleteMapping("/students")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<?> delete(@RequestParam(name = "id") String id) {
        return ResponseEntity.ok(studentService.delete(id));
    }


}
