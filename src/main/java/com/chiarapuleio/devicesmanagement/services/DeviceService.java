package com.chiarapuleio.devicesmanagement.services;

import com.chiarapuleio.devicesmanagement.dao.DeviceDAO;
import com.chiarapuleio.devicesmanagement.entities.Device;
import com.chiarapuleio.devicesmanagement.entities.Employee;
import com.chiarapuleio.devicesmanagement.enums.DeviceStatus;
import com.chiarapuleio.devicesmanagement.exceptions.NotFoundException;
import com.chiarapuleio.devicesmanagement.payloads.DeviceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;

    public Device save(DeviceDTO device){
        Device newDev = new Device();
        newDev.setType(device.type());
        newDev.setDeviceStatus(DeviceStatus.AVAILABLE);

        return deviceDAO.save(newDev);
    }

    public Device findById(UUID id){
        return deviceDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Device findByIdAndUpdate(UUID id, Device device){
        Device devFound = this.findById(id);
        devFound.setType(device.getType());
        devFound.setDeviceStatus(device.getDeviceStatus());

        return deviceDAO.save(devFound);
    }

    public void findByIdAndDelete(UUID id){
        Device devFound = this.findById(id);
        deviceDAO.delete(devFound);
    }
}
