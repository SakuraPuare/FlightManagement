package com.sakurapuare.flightmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Flight {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "airline_id")
    private Long airlineId;

    @TableField(value = "flight_number")
    private String flightNumber;

    @TableField(value = "capacity")
    private int capacity;

    @TableField(value = "departure_city")
    private String departureCity;

    @TableField(value = "arrival_city")
    private String arrivalCity;

    @TableField(value = "date_of_departure")
    private LocalDateTime dateOfDeparture;

    @TableField(value = "estimated_travel_time")
    private int estimatedTravelTime;
}
