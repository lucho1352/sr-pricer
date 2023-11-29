package com.inditex.pricer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CURRENCY")
@NoArgsConstructor
public class Currency {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CURR_DESC")
    private String shortDescription;

}
