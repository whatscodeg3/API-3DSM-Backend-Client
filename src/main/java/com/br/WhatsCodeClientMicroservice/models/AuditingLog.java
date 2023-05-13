package com.br.WhatsCodeClientMicroservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class AuditingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String entityType;
    @Column
    private Long entityId;
    @Column
    private String deletedBy;
    @Column
    private Date deletedAt;

}
