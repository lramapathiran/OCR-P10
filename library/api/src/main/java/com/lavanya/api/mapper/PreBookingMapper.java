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
    PreBooking preBookingDtoToPreBooking(PreBookingDto preBookingDto);

    @Mapping(source = "book", target = "bookDto")
    PreBookingDto preBookingToPreBookingDto(PreBooking preBooking);

    @Mapping(source = "book", target = "bookDto")
    List<PreBookingDto> listPreBookingToListPreBookingDto(List<PreBooking> listPreBooking);

//    @Named("mapWithoutLendings")
//    @Mapping(target = "book.lendings", ignore = true)
//    PreBookingDto mapWithoutLendings(PreBooking source);
}
