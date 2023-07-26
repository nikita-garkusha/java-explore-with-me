package ru.practicum.stats;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.practicum.StatsClient;
import ru.practicum.dto.EndpointHitDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class StatsSender {
    final StatsClient statsClient;
    @Value("${main.service.name}")
    String serviceName;

    public void send(HttpServletRequest request) {
        statsClient.post(new EndpointHitDto(null,
                serviceName,
                request.getRequestURI(),
                request.getRemoteAddr(),
                LocalDateTime.now()));
    }
}
