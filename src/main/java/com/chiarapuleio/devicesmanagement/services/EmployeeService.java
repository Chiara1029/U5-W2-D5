package com.chiarapuleio.devicesmanagement.services;

import com.chiarapuleio.devicesmanagement.dao.EmployeeDAO;
import com.chiarapuleio.devicesmanagement.entities.Employee;
import com.chiarapuleio.devicesmanagement.exceptions.BadRequestException;
import com.chiarapuleio.devicesmanagement.exceptions.NotFoundException;
import com.chiarapuleio.devicesmanagement.payloads.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    public Employee save(EmployeeDTO employee) throws IOException {
        employeeDAO.findByEmail(employee.email()).ifPresent(emp -> {
            throw new BadRequestException("Email: " + employee.email() + " already exist.");
        });
        Employee newEmp = new Employee();
        newEmp.setName(employee.name());
        newEmp.setSurname(employee.surname());
        newEmp.setEmail(employee.email());
        newEmp.setUsername(employee.username());

        return employeeDAO.save(newEmp);
    }

    public Employee findById(UUID id){
        return employeeDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Employee findByIdAndUpdate(UUID id, Employee employee){
        Employee empFound = this.findById(id);
        empFound.setUsername(employee.getUsername());
        empFound.setName(employee.getName());
        empFound.setSurname(employee.getSurname());
        empFound.setEmail(employee.getEmail());

        return employeeDAO.save(empFound);
    }

    public void findByIdAndDelete(UUID id){
        Employee empFound = this.findById(id);
        employeeDAO.delete(empFound);
    }
}
