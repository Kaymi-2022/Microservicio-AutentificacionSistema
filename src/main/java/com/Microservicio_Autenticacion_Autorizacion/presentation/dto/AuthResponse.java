package com.Microservicio_Autenticacion_Autorizacion.presentation.dto;

public record AuthResponse(String token, String username, String authorities) {
    @Override
    public String toString() {
        return "token:'\n" + token + '\n' +
              "username:'\n" + username + '\n'+
              "Autorities:'\n" + authorities + '\n';
    }
}
