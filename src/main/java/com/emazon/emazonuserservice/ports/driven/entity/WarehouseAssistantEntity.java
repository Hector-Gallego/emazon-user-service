package com.emazon.emazonuserservice.ports.driven.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "warehouse_assistant")
public class WarehouseAssistantEntity extends UserEntity {

}
