package ru.practicum.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdatedDto {
    private List<RequestDto> confirmedRequests;
    private List<RequestDto> rejectedRequests;
}
