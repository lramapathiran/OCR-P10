package com.lavanya.api.service;

import com.lavanya.api.model.Notified;
import com.lavanya.api.model.PreBooking;
import com.lavanya.api.repository.NotifiedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service provider for all business functionalities related to Notified class.
 * @author lavanya
 */
@Service
public class NotifiedService {

    @Autowired
    NotifiedRepository notifiedRepo;

    public void saveNotified(Notified notified) {
        notifiedRepo.save(notified);
    }

    public Optional<Notified> getNotifiedByPreBookingId(int preBookingId) {
        return notifiedRepo.findByPreBookingId(preBookingId);
    }
}
