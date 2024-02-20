package edu.inventory.core.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmedicine;
    private String medicine_name;
    private Date medicine_exp_date;
    private String medicine_type;
    private String medicine_stock;
    private Long iduser;
}
