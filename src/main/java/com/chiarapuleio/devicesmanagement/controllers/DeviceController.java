package com.chiarapuleio.devicesmanagement.controllers;

import com.chiarapuleio.devicesmanagement.dao.DeviceDAO;
import com.chiarapuleio.devicesmanagement.entities.Device;
import com.chiarapuleio.devicesmanagement.entities.Employee;
import com.chiarapuleio.devicesmanagement.exceptions.BadRequestException;
import com.chiarapuleio.devicesmanagement.payloads.DeviceDTO;
import com.chiarapuleio.devicesmanagement.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService devSrv;
    @Autowired
    private DeviceDAO deviceDAO;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody @Validated DeviceDTO device, BindingResult validation) throws Exception {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return devSrv.save(device);
    }

    @GetMapping
    public List<Device> getDevices(){
        return deviceDAO.findAll();
    }

    @GetMapping("/{id}")
    public Device findById(@PathVariable UUID id){
        return devSrv.findById(id);
    }

    @PutMapping("/{id}")
    public Device findAndUpdate(@PathVariable UUID id, @RequestBody Device device){
        return devSrv.findByIdAndUpdate(id, device);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID id){
        devSrv.findByIdAndDelete(id);
    }

    @PatchMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Device setDeviceEmployee(@PathVariable UUID deviceId, @RequestBody UUID employeeId){
        return devSrv.setDeviceEmployee(employeeId, deviceId);
    }

}
