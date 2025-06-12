package com.Microservicio_Autenticacion_Autorizacion.presentation.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
