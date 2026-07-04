package com.example.IotTelemetryApi;

import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceRepository repository;


    public DeviceController(DeviceRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    public Device createDevice(@RequestBody Device device) {
        device.setLastSeen(Instant.now());
        return repository.save(device);
    }


    @GetMapping
    public List<Device> getAllDevices() {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    public Device getDevice(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }
}