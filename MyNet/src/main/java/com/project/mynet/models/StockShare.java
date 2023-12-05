package com.project.mynet.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StockShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
}
