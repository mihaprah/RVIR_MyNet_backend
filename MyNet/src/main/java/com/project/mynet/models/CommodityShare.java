package com.project.mynet.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CommodityShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;
}
