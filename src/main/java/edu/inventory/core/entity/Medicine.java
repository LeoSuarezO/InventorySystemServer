package edu.inventory.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idmedicine;
    private String medicine_name;
    private Date medicine_exp_date;
    private String medicine_type;
    private String medicine_stock;
}
