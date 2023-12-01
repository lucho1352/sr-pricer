package com.inditex.pricer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "CURRENCY")
@NoArgsConstructor
@AllArgsConstructor
public class Currency {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPTION")
    private String shortDescription;

}
