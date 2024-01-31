package com.peliculas.tmdbapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO que se utiliza para el logueo de usuarios. Contiene el nombre de usuario y el token que se genera para el acceso
 * del mismo.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDto {
    private String nombreUsuario;
    private String token;
}