package com.api1.payload;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmployeeDto {
    private String empName;
    private String email;
    private String mobile;


}
