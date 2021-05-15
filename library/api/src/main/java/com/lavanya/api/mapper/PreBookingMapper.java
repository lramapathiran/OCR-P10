package com.lavanya.api.mapper;

import com.lavanya.api.dto.PreBookingDto;
import com.lavanya.api.model.PreBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PreBookingMapper {

    PreBookingMapper INSTANCE = Mappers.getMapper(PreBookingMapper.class);

    @Mapping(source = "bookDto", target = "book")
    PreBooking preBookingDtoToPreBooking(PreBookingDto preBookingDto);

    @Mapping(source = "book", target = "bookDto")
    PreBookingDto preBookingToPreBookingDto(PreBooking preBooking);
}
