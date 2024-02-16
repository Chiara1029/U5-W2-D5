package com.chiarapuleio.devicesmanagement.services;

import com.chiarapuleio.devicesmanagement.dao.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;
}
