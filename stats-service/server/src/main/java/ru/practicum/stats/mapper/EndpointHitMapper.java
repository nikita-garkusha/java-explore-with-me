package ru.practicum.stats.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.stats.model.EndpointHit;

@Mapper(componentModel = "spring")
public interface EndpointHitMapper {
    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "timestamp", target = "timestamp", dateFormat = DATE_FORMAT)
    EndpointHit toEndpointHit(EndpointHitDto endpointHitDto);

    @Mapping(source = "timestamp", target = "timestamp", dateFormat = DATE_FORMAT)
    EndpointHitDto toEndpointHitDto(EndpointHit endpointHit);
}
