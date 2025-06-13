package com.Microservicio_Autenticacion_Autorizacion.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassworEncryptor {

    public static void main(String[] args) {
        String password = "admin123"; // Cambia esta contraseña por la que quieras encriptar
        String encryptedPassword = encryptPassword(password);
        System.out.println("Contraseña original: " + password);
        System.out.println("Contraseña encriptada: " + encryptedPassword);
    }

    public static String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }


}
