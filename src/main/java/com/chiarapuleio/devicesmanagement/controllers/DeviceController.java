package com.chiarapuleio.devicesmanagement.controllers;

import com.chiarapuleio.devicesmanagement.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService devSrv;
}
