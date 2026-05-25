package com.expenseService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonProperty("enternal_id")
    private String externalId;
    private String amount;
    @JsonProperty("user_id")
    private String userId;
    private String merchant;
    private String currency;
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (externalId == null) {
            this.externalId = String.valueOf(UUID.randomUUID());
        }
    }
}
