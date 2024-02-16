package com.chiarapuleio.devicesmanagement.services;

import com.chiarapuleio.devicesmanagement.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;
}
