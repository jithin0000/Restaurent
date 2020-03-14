package com.restuarent.Restaurent.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class BaseModel {

    @Id
    private String id;

    private Date created = new Date();
    private Date modified = new Date();
}
