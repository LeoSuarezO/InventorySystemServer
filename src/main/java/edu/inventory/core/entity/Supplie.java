package edu.inventory.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Supplie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idsupplies;
    private String supplie_name;
    private String supplie_type;
    private String supplie_stock;


}
