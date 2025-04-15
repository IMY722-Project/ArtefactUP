package za.ac.up.artifactup.dto.mapper;

import org.mapstruct.*;
import za.ac.up.artifactup.dto.*;
import za.ac.up.artifactup.entity.*;

import java.time.DayOfWeek;

@Mapper(componentModel = "spring")
public interface OpeningHoursMapper {

    @Mapping(target = "openingTime", dateFormat = "HH:mm")
    @Mapping(target = "closingTime", dateFormat = "HH:mm")
    OpeningHoursDTO toDto(OpeningHours openingHours);

    @Mapping(target = "openingTime", dateFormat = "HH:mm")
    @Mapping(target = "closingTime", dateFormat = "HH:mm")
    @Mapping(target = "day", expression = "java(mapToDayOfWeek(openingHoursDTO))")
    OpeningHours toEntity(OpeningHoursDTO openingHoursDTO);

    default DayOfWeek mapToDayOfWeek(OpeningHoursDTO openingHoursDTO) {
        return DayOfWeek.valueOf(openingHoursDTO.getDay().toUpperCase());
    }
}

