package com.lavanya.web.dto;

import java.time.LocalDate;
import java.util.OptionalInt;

/**
 * Bean representing an object containing PrebookingDto of a user and all related data.
 * UserPreBookingData has all attributes required for a user to know is position in waiting list
 * and the earliest due date for the book pre-booked.
 * @author lavanya
 */
public class UserPreBookingData {

    private LocalDate time;
    private int position;
    private PreBookingDto preBookingDto;

    public UserPreBookingData(LocalDate time, int position, PreBookingDto preBookingDto) {
        this.time = time;
        this.position = position;
        this.preBookingDto = preBookingDto;
    }

    public UserPreBookingData() {
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public PreBookingDto getPreBookingDto() {
        return preBookingDto;
    }

    public void setPreBookingDto(PreBookingDto preBookingDto) {
        this.preBookingDto = preBookingDto;
    }
}
