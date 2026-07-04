package com.example.IotTelemetryApi;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;

public interface TelemetryReadingRepository extends JpaRepository<TelemetryReading, Long> {

    List<TelemetryReading> findByDeviceIdAndTimestampBetweenOrderByTimestampAsc(
            Long deviceId, Instant from, Instant to);
}