package com.products.backend.infra.exceptions;

import lombok.Getter;

@SuppressWarnings({"serial"})

@Getter
public class DomainException extends RuntimeException {

    private Enum<?> errorCode;
    private Object[] messageParams;
    private DomainErrorDetails errorDetails;
    private boolean formatted;

    public DomainException(Enum<?> errorCode) {
        super("");
        this.errorCode = errorCode;
    }

    public DomainException(Enum<?> errorCode, Object[] messageParams) {
        super("");
        this.errorCode = errorCode;
        this.messageParams = messageParams;
    }

    public DomainException(Enum<?> errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public DomainException(Enum<?> errorCode, String message, boolean formatted) {
        super(message);
        this.errorCode = errorCode;
        this.formatted = formatted;
    }

    public DomainException(Enum<?> errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public DomainException(DomainErrorDetails errorDetails) {
        super(errorDetails.getMessage());
        //this.errorDetails = errorDetails;
    }
}
