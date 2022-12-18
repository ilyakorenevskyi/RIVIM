package com.ilyakor.lab2.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class SubscriptionRequest {
    private long periodicalId;
    private int period;
}
