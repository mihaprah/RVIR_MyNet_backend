package com.project.mynet.dto;

import lombok.Data;

@Data
public class ClientDTO {

//    NOT SURE IF THIS CLASS IS NECESSARY....

    private Long id;
    private String name;
    private String lastname;
    private String address;
    private double cashBalance;
    private String email;
}
