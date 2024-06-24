package com.riwi.librosya.api.dto.errors;

import java.util.List;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorResponse extends BaseErrorResponse {
    private List<Map<String, String>> errors;
}
