package com.lavanya.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Bean representing a data transfer Object PrebookingDto.
 * PreBookingDto has all attributes required for pre-booking a book.
 * @author lavanya
 */
public class PreBookingDto {

    private Integer id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    private BookDto bookDto;

    private UserDto userDto;

    public PreBookingDto() {
    }

    public  PreBookingDto(Integer id, LocalDateTime time, UserDto userDto, BookDto bookDto) {
        this.id = id;
        this.time = time;
        this.userDto = userDto;
        this.bookDto = bookDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
