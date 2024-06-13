package com.microservicios.wastemanagerservice.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "center_authorization")
@Entity
public class WasteCenterAuthorizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorizationNumber;
}
