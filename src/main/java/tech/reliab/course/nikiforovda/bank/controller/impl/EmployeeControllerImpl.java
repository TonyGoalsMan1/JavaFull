package tech.reliab.course.nikiforovda.bank.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.reliab.course.nikiforovda.bank.entity.Employee;
import tech.reliab.course.nikiforovda.bank.model.EmployeeRequest;
import tech.reliab.course.nikiforovda.bank.service.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeControllerImpl {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(EmployeeRequest employeeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employeeRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(int id, String name) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(int id) {
        return ResponseEntity.ok(employeeService.getEmployeeDtoById(id));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
