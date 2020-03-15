package com.restuarent.Restaurent.models;

import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document
public class Property extends BaseModel {

    @NotNull(message="name is required field ")
    private String name;
    @NotNull(message="description is required field ")
    private String description;
    @NotNull(message = "PropertyType is required field ")
    private PropType PropertyType;
    @NotNull(message = "roomType is required field ")
    private RoomType roomType;
    @NotNull(message = "address is required field ")
    private Address address;
    @NotNull(message="location is required field ")
    private GeoJsonPoint location;
    @NotNull(message = "bedRoomCount is required field ")
    private int bedRoomCount = 0;
    @NotNull(message = "bath room count is required field")
    private int bathRoomCount = 0;
    @NotNull(message = "accommodate is required field ")
    private int accommodate=0;

    @NotNull(message = "amenities is required field ")
    private List<String> amenities = new ArrayList<>();

    @NotNull(message="price is required field ")
    private int price;
    @NotNull(message="startDate is required field ")
    private Date startDate;
    @NotNull(message="endDate is required field ")
    private Date endDate;

    @NotNull(message = "refundable is required field ")
    private boolean refundable = false;
    @NotNull(message = "status is required field ")
    private boolean status = false;
    // TODO: 15/03/20 need to change status and refundable into enum
    @NotNull(message="extraPeople is required field ")
    private int extraPeople = 0;
    @NotNull(message = "guestInclude is required field ")
    private int guestInclude = 0;

    private List<Review> reviews = new ArrayList<>();




}















