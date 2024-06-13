package com.categories.backend.infra.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class DomainErrorDetails {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}
