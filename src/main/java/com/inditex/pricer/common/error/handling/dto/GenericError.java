package com.inditex.pricer.common.error.handling.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericError {

    private Integer code;
    private String errorMessage;

}
