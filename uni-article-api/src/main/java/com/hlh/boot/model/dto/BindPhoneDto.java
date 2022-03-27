package com.hlh.boot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BindPhoneDto {
    private String phone;
    private String code;
    private String wxOpenId;
}
