package com.expenseService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExpenseDto {
    @JsonProperty("external_id")
    private String externalId;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("merchant")
    private String merchant;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("created_at")
    private Timestamp createdAt;
}
