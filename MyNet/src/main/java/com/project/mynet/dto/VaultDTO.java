package com.project.mynet.dto;

import com.project.mynet.models.Vault;
import lombok.Data;

import java.util.Date;

@Data
public class VaultDTO {

    private Long id;
    private String name;
    private double goal;
    private double amount;
    private Date dueDate;
    private String icon;

}
