package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.mapper.FlightMapper;
import com.sakurapuare.flightmanagement.mapper.user.AirlineMapper;
import com.sakurapuare.flightmanagement.pojo.dto.FlightDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class FlightControllerTest {

    @Mock
    private FlightService flightService;

    @Mock
    private FlightMapper flightMapper;

    @Mock
    private AirlineMapper airlineMapper;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getFlightList_shouldReturnListOfFlights() {
        // Arrange
        PaginationDTO paginationDTO = new PaginationDTO();
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight());
        flights.add(new Flight());
        when(flightService.getFlightsByPagination(any(PaginationDTO.class))).thenReturn(flights);

        // Act
        Response<List<Flight>> response = flightController.getFlightList(paginationDTO);

        // Assert
        assertEquals(Response.success(flights), response);
        verify(flightService, times(1)).getFlightsByPagination(any(PaginationDTO.class));
    }

    @Test
    void getFlightById_shouldReturnFlightById() {
        // Arrange
        Long id = 1L;
        Flight flight = new Flight();
        when(flightMapper.selectById(anyLong())).thenReturn(flight);

        // Act
        Response<Flight> response = flightController.getFlightById(id);

        // Assert
        assertEquals(Response.success(flight), response);
        verify(flightMapper, times(2)).selectById(anyLong());
    }

    @Test
    void getFlightById_shouldReturnErrorWhenFlightNotFound() {
        // Arrange
        Long id = 1L;
        when(flightMapper.selectById(anyLong())).thenReturn(null);

        // Act
        Response<Flight> response = flightController.getFlightById(id);

        // Assert
        assertEquals(Response.error("Flight not found"), response);
        verify(flightMapper, times(1)).selectById(anyLong());
    }

    @Test
    void searchFlight_shouldReturnListOfFlights() {
        // Arrange
        String query = "search query";
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight());
        flights.add(new Flight());
        when(flightService.search(anyString())).thenReturn(flights);

        // Act
        Response<List<Flight>> response = flightController.searchFlight(query);

        // Assert
        assertEquals(Response.success(flights), response);
        verify(flightService, times(1)).search(anyString());
    }

    @Test
    void addFlight_shouldAddFlightSuccessfully() {
        // Arrange
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setAirlineId(1L);
        Airline airline = new Airline();
        when(airlineMapper.selectById(anyLong())).thenReturn(airline);
        Flight flight = new Flight();
        when(flightMapper.insert(any(Flight.class))).thenReturn(1);

        // Act
        Response<Void> response = flightController.addFlight(flightDTO);

        // Assert
        assertEquals(Response.success("Flight added successfully"), response);
        verify(airlineMapper, times(1)).selectById(anyLong());
        verify(flightMapper, times(1)).insert(any(Flight.class));
    }

    @Test
    void addFlight_shouldReturnErrorWhenAirlineNotFound() {
        // Arrange
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setAirlineId(1L);
        when(airlineMapper.selectById(anyLong())).thenReturn(null);

        // Act
        Response<Void> response = flightController.addFlight(flightDTO);

        // Assert
        assertEquals(Response.error("Airline not found"), response);
        verify(airlineMapper, times(1)).selectById(anyLong());
        verify(flightMapper, never()).insert(any(Flight.class));
    }

    @Test
    void updateFlight_shouldUpdateFlightSuccessfully() {
        // Arrange
        Long id = 1L;
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setAirlineId(1L);
        Flight flight = new Flight();
        when(flightMapper.selectById(anyLong())).thenReturn(flight);
        Airline airline = new Airline();
        when(airlineMapper.selectById(anyLong())).thenReturn(airline);
        when(flightMapper.updateById(any(Flight.class))).thenReturn(1);

        // Act
        Response<Void> response = flightController.updateFlight(id, flightDTO);

        // Assert
        assertEquals(Response.success("Flight updated successfully"), response);
        verify(flightMapper, times(1)).selectById(anyLong());
        verify(airlineMapper, times(1)).selectById(anyLong());
        verify(flightMapper, times(1)).updateById(any(Flight.class));
    }

    @Test
    void updateFlight_shouldReturnErrorWhenFlightNotFound() {
        // Arrange
        Long id = 1L;
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setAirlineId(1L);
        when(flightMapper.selectById(anyLong())).thenReturn(null);

        // Act
        Response<Void> response = flightController.updateFlight(id, flightDTO);

        // Assert
        assertEquals(Response.error("Flight not found"), response);
        verify(flightMapper, times(1)).selectById(anyLong());
        verify(airlineMapper, never()).selectById(anyLong());
        verify(flightMapper, never()).updateById(any(Flight.class));
    }

    @Test
    void updateFlight_shouldReturnErrorWhenAirlineNotFound() {
        // Arrange
        Long id = 1L;
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setAirlineId(1L);
        Flight flight = new Flight();
        when(flightMapper.selectById(anyLong())).thenReturn(flight);
        when(airlineMapper.selectById(anyLong())).thenReturn(null);

        // Act
        Response<Void> response = flightController.updateFlight(id, flightDTO);

        // Assert
        assertEquals(Response.error("Airline not found"), response);
        verify(flightMapper, times(1)).selectById(anyLong());
        verify(airlineMapper, times(1)).selectById(anyLong());
        verify(flightMapper, never()).updateById(any(Flight.class));
    }

    @Test
    void deleteFlight_shouldDeleteFlightSuccessfully() {
        // Arrange
        Long id = 1L;
        Flight flight = new Flight();
        when(flightMapper.selectById(anyLong())).thenReturn(flight);

        // Act
        Response<Void> response = flightController.deleteFlight(id);

        // Assert
        assertEquals(Response.success("Flight deleted successfully"), response);
        verify(flightMapper, times(1)).selectById(anyLong());
        verify(flightMapper, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteFlight_shouldReturnErrorWhenFlightNotFound() {
        // Arrange
        Long id = 1L;
        when(flightMapper.selectById(anyLong())).thenReturn(null);

        // Act
        Response<Void> response = flightController.deleteFlight(id);

        // Assert
        assertEquals(Response.error("Flight not found"), response);
        verify(flightMapper, times(1)).selectById(anyLong());
        verify(flightMapper, never()).deleteById(anyLong());
    }
}