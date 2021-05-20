package com.lavanya.api.model;


import javax.persistence.*;
import java.time.LocalDate;

/**
 * Bean representing Notified.
 * Notified object is declared as a JPA entity with the corresponding annotation.
 * @author lavanya
 */
@Entity
public class Notified {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="prebooking_id")
    private Integer preBookingId;

    @Column(name="notified_date")
    private LocalDate notifiedDate;

    public Notified() {
    }

    public Notified(Integer userId, Integer preBookingId, LocalDate notifiedDate) {
        this.userId = userId;
        this.preBookingId = preBookingId;
        this.notifiedDate = notifiedDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPreBookingId() {
        return preBookingId;
    }

    public void setPreBookingId(Integer preBookingId) {
        this.preBookingId = preBookingId;
    }

    public LocalDate getNotifiedDate() {
        return notifiedDate;
    }

    public void setNotifiedDate(LocalDate notifiedDate) {
        this.notifiedDate = notifiedDate;
    }
}
