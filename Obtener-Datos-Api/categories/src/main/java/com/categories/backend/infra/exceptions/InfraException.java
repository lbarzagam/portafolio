package com.categories.backend.infra.exceptions;

import lombok.Getter;

@SuppressWarnings({"serial"})

@Getter
public class InfraException extends RuntimeException {

    private Enum<?> errorCode;
    private Object[] messageParams;
    private DomainErrorDetails errorDetails;

    public InfraException(Enum<?> errorCode) {
        super("");
        this.errorCode = errorCode;
    }

    public InfraException(Enum<?> errorCode, Object[] messageParams) {
        super("");
        this.errorCode = errorCode;
        this.messageParams = messageParams;
    }

    public InfraException(Enum<?> errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public InfraException(DomainErrorDetails errorDetails) {
        super(errorDetails.getMessage());
        //this.errorDetails = errorDetails;
    }
}
