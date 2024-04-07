package org.example.service;

import org.example.exception.BookingException;
import org.example.model.Availability;
import org.example.model.BookingRequest;
import org.example.model.BookingResult;
import org.example.model.SearchCriteria;

import java.util.List;

public interface BookableEntityService<T> {
    void register(T entity);
    void updateAvailability(int entityId, List<Availability> availability);
    List<T> search(SearchCriteria criteria);
    BookingResult book(int entityId, BookingRequest bookingRequest) throws BookingException;
}
