package com.api1.service;

import com.api1.entity.Employee;
import com.api1.exception.ResourceNotFound;
import com.api1.payload.EmployeeDto;
import com.api1.repository.EmployeeRepository;
import jakarta.servlet.Registration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    public List<EmployeeDto> getAll() {
        List<Employee> listAll = employeeRepository.findAll();
        List<EmployeeDto> dtos = listAll.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee emp=mapToEntity(employeeDto);
        Employee save = employeeRepository.save(emp);

        EmployeeDto dtos=mapToDto(save);
        return dtos;
    }

    public void deleteEmp(Long id) {
        employeeRepository.deleteById(id);

    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee e = employeeRepository.findById(id).get();
        e.setEmpName(employeeDto.getEmpName());
        e.setEmail(employeeDto.getEmail());
        e.setMobile(employeeDto.getMobile());
        Employee save = employeeRepository.save(e);
        EmployeeDto dtos=mapToDto(save);
        return dtos;
    }
    Employee mapToEntity(EmployeeDto employeeDto){
          Employee employee=new Employee();
        employee.setEmpName(employeeDto.getEmpName());
        employee.setEmail(employeeDto.getEmail());
        employee.setMobile(employeeDto.getMobile());
         return employee;
    }

    EmployeeDto mapToDto(Employee employee){
        EmployeeDto dto=new EmployeeDto();
        dto.setEmpName(employee.getEmpName());
        dto.setEmail(employee.getEmail());
        dto.setMobile(employee.getMobile());
        return dto;

    }

    public EmployeeDto findById(long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFound("Record not found"));
        return mapToDto(emp);
    }
}
