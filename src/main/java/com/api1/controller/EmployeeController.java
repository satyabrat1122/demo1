package com.api1.controller;

import com.api1.entity.Employee;
import com.api1.payload.EmployeeDto;
import com.api1.service.EmployeeService;
import jakarta.servlet.Registration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;


    }
   @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllRegistration(){

        List<EmployeeDto> employeeDto=employeeService.getAll();
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(
            @RequestBody EmployeeDto employeeDto
    ){
        EmployeeDto saved = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);

    }
    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(
            @RequestParam Long id
    ){
        employeeService.deleteEmp(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto
    ){
        EmployeeDto updateE = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(updateE,HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable long id){
        EmployeeDto dtos=employeeService.findById(id);
        return new ResponseEntity<>(dtos,HttpStatus.OK);

    }

}
