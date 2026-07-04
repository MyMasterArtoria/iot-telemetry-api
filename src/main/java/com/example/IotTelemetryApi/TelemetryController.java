package com.example.IotTelemetryApi;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/devices/{deviceId}/telemetry")
public class TelemetryController {

    private final DeviceRepository deviceRepository;
    private final TelemetryReadingRepository telemetryRepository;

    public TelemetryController(DeviceRepository deviceRepository,
                               TelemetryReadingRepository telemetryRepository) {
        this.deviceRepository = deviceRepository;
        this.telemetryRepository = telemetryRepository;
    }


    @PostMapping
    public TelemetryReading addReading(@PathVariable Long deviceId,
                                       @Valid @RequestBody TelemetryRequest request) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));

        TelemetryReading reading = new TelemetryReading();
        reading.setDevice(device);
        reading.setMetric(request.getMetric());
        reading.setValue(request.getValue());
        reading.setTimestamp(Instant.now());

        // update lastSeen
        device.setLastSeen(Instant.now());
        deviceRepository.save(device);

        return telemetryRepository.save(reading);
    }


    @GetMapping
    public List<TelemetryReading> getReadings(
            @PathVariable Long deviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to) {

        if (!deviceRepository.existsById(deviceId)) {
            throw new DeviceNotFoundException(deviceId);
        }
        return telemetryRepository
                .findByDeviceIdAndTimestampBetweenOrderByTimestampAsc(deviceId, from, to);
    }
}
