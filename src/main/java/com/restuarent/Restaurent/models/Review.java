package com.restuarent.Restaurent.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document
public class Review {

    @NotNull(message = "propertyId is required field ")
    private String propertyId;
    @NotNull(message="userId is required field ")
    private String userId;
    @NotNull(message="username is required field ")
    private String username;
    @NotNull(message="comment is required field ")
    private String comment;
    @NotNull(message="reviewScore is required field ")
    private int reviewScore;

}
