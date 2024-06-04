package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.services.FlightService;
import com.sakurapuare.flightmanagement.services.user.AirlineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FlightControllerTest {

    private FlightController flightController;

    @Mock
    private FlightService flightService;

    @Mock
    private AirlineService airlineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        flightController = new FlightController(flightService, airlineService);
    }

    @Test
    void testGetFlightById() {
        // Arrange
        Long flightId = 1L;
        Flight flight = new Flight();
        flight.setId(flightId);
        when(flightService.findFlightById(flightId)).thenReturn(flight);

        // Act
        Response<Flight> response = flightController.getFlightById(flightId);

        // Assert
        assertEquals(Response.success(flight), response);
    }

}