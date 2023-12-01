package com.inditex.pricer.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Entity
@Builder
@Table(name = "BRAND")
@NoArgsConstructor
@AllArgsConstructor
public class Brand implements Serializable {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;
}
