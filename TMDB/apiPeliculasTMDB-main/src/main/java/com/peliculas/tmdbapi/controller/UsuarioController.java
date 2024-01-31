package com.peliculas.tmdbapi.controller;


import com.peliculas.tmdbapi.configuration.SecurityConfig;
import com.peliculas.tmdbapi.dto.UsuarioLoginDto;
import com.peliculas.tmdbapi.entities.users.Usuario;
import com.peliculas.tmdbapi.model.ResponseMessage;
import com.peliculas.tmdbapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Clase controller que maneja el registro y logueo de usuarios
 */
@RestController
public class UsuarioController {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Permite el logueo de usuarios en el sistema y retorna una response con un dto que contiene el token
     *
     * @param usuario
     * @return ResponseMessage<UsuarioLoginDto>
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseMessage<UsuarioLoginDto>> login(@RequestBody Usuario usuario) {
        UsuarioLoginDto userLoginDto = new UsuarioLoginDto();

        try {
            if (usuarioService.logueoUsuario(usuario)) {
                String token = securityConfig.getJWTToken(usuario.getNombreUsuario());
                userLoginDto.setNombreUsuario(usuario.getNombreUsuario());
                userLoginDto.setToken(token);

            }else if(!usuarioService.logueoUsuario(usuario)){
                userLoginDto.setNombreUsuario(usuario.getNombreUsuario());
                userLoginDto.setToken(null);
                return this.respuestaConflict(userLoginDto, "El nombre de usuario o la contraseña son incorrectos");
            }

        }catch (Exception e) {
            e.printStackTrace();
            return this.respuestaConflict(userLoginDto, "Error en el login");
        }

        return this.respuestaOk(userLoginDto, "Bienvenido"); //ver de retornar error cuando no este ok el login
    }

    /**
     * Permite el registro de un nuevo usuario, valida que el nombre de usuario no este ya en uso y si no esta en uso lo guarda
     *
     * @param usuario
     * @return  ResponseEntity<ResponseMessage<UsuarioLoginDto>>
     */
    @PostMapping(value="/register")
    public ResponseEntity<ResponseMessage<UsuarioLoginDto>> register(@RequestBody Usuario usuario) {

        Usuario usuarioCreado = new Usuario(); // usuario creado
        UsuarioLoginDto usuarioLoginDtoCreado = new UsuarioLoginDto();

        try {
            usuarioCreado = usuarioService.crearUsuario(usuario);

            if(usuarioCreado != null){
                usuarioLoginDtoCreado = this.login(usuarioCreado).getBody().getData();

                return this.respuestaOk(usuarioLoginDtoCreado, "Usuario creado correctamente");
            }else{
                String mensaje = "El nombre de usuario ya se encuentra en uso";
                usuarioLoginDtoCreado.setNombreUsuario(usuario.getNombreUsuario());

                return this.respuestaConflict(usuarioLoginDtoCreado, mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.respuestaConflict(usuarioLoginDtoCreado, "Se ha producido un error al guardar el usuario");
        }

    }

    /**
     * Respuesta en caso de que se genere el usuario ok
     *
     * @param usuarioARetornar
     * @param mensajeAdicional
     * @return ResponseMessage<UsuarioLoginDto>
     */
    public ResponseEntity<ResponseMessage<UsuarioLoginDto>> respuestaOk(UsuarioLoginDto usuarioARetornar, String mensajeAdicional) {
        ResponseMessage<UsuarioLoginDto> responseMessage = new ResponseMessage<>(usuarioARetornar, mensajeAdicional);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseMessage);
    }


    /**
     * Genera y devuelve un ResponseEntity en caso de que se produzca un error en la creación del usuario
     *
     * @param usuarioARetornar
     * @param mensajeAdicional
     * @return ResponseEntity.status(HttpStatus.CONFLICT)
     */
    public ResponseEntity<ResponseMessage<UsuarioLoginDto>> respuestaConflict(UsuarioLoginDto usuarioARetornar, String mensajeAdicional) {
        ResponseMessage<UsuarioLoginDto> responseMessage = new ResponseMessage<>(usuarioARetornar, mensajeAdicional);

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(responseMessage);
    }
}