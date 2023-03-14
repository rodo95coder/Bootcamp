package com.bootcamp.currentaccount.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GeneralException {
    private String message;
}
