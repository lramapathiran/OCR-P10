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

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDateTime time;

    private BookDto bookDto;

    public PreBookingDto() {
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

    @Override
    public String toString() {
        return "PreBookingDto{" +
                "id=" + id +
                ", time=" + time +
                ", bookDto=" + bookDto +
                '}';
    }
}
