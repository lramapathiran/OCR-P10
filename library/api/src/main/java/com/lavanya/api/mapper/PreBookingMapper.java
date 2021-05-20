package com.lavanya.api.mapper;

import com.lavanya.api.dto.PreBookingDto;
import com.lavanya.api.model.Book;
import com.lavanya.api.model.PreBooking;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PreBookingMapper {

    PreBookingMapper INSTANCE = Mappers.getMapper(PreBookingMapper.class);

    @Mapping(source = "bookDto", target = "book")
    @Mapping(source = "userDto", target = "user")
    PreBooking preBookingDtoToPreBooking(PreBookingDto preBookingDto);

    @Mapping(source = "book", target = "bookDto")
    @Mapping(source = "user", target = "userDto")
    PreBookingDto preBookingToPreBookingDto(PreBooking preBooking);

    @Mapping(source = "book", target = "bookDto")
    @Mapping(source = "user", target = "userDto")
    List<PreBookingDto> listPreBookingToListPreBookingDto(List<PreBooking> listPreBooking);

}
