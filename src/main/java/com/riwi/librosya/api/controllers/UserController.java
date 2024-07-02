package com.riwi.librosya.api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.librosya.api.dto.errors.ErrorResponse;
import com.riwi.librosya.api.dto.request.UserRequest;
import com.riwi.librosya.api.dto.response.UserResponse;
import com.riwi.librosya.infrastructure.services.UserService;
import com.riwi.librosya.util.enums.SortType;
import com.riwi.librosya.util.messages.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping(path = "/users")
@Data
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    /* Create */
        @PostMapping
        @Operation(summary = "Crear un usuario")
        @ApiResponse(
            responseCode = "400", 
            description = "Cuando el id no es valido", 
            content = {
                @Content(
                    mediaType = "application/json", 
                    schema = @Schema(implementation = ErrorResponse.class)) })
        public ResponseEntity<UserResponse> onsecreate(
            @Validated UserRequest request
        ){
            return ResponseEntity.ok(this.userService.create(request));
        }
    
    /*Read */   
        @GetMapping
        @Operation(summary = "Obtiene los usuarios de forma paginada y organizada por el nombre")
        public ResponseEntity<Page<UserResponse>>onsegetAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestHeader(required = false)SortType sortType
        ){
            if (Objects.isNull(sortType)) {
                sortType = SortType.NONE;
            }
            return ResponseEntity.ok(this.userService.getAll(page, size, sortType));
        }

        @GetMapping(path = "/{id}")
        @Operation(summary = "Obtiene el usuario por id")
        @ApiResponse(
            responseCode = "400", 
            description = "el id no es valido", 
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorMessage.class)
                )
            }
        )
        public ResponseEntity<UserResponse> onsegetById(@PathVariable Long id){
            return ResponseEntity.ok(this.userService.getById(id));
        }
    
    /* update */
        @PutMapping(path = "/{id}")
        @Operation(summary = "Actualiza el usuario por id")
        @ApiResponse(
            responseCode = "400", 
            description = "Cuando el id no es valido", 
            content = { 
                @Content(
                    mediaType = "application/json", 
                    schema = @Schema(implementation = ErrorResponse.class))
                }
        )
        public ResponseEntity<UserResponse> onseupdate(
            @PathVariable Long id,
            @Validated @RequestBody UserRequest request
            ){
                return ResponseEntity.ok(this.userService.update(request, id));
        }

    /* delete */
        @DeleteMapping(path = "/{id}")
        @Operation(summary = "Elimina el usuario por id")
        @ApiResponse(
            responseCode = "400", 
            description = "Cuando el id no es valido", 
            content = { 
                @Content(
                    mediaType = "application/json", 
                    schema = @Schema(implementation = ErrorResponse.class))
                }
        )
        public ResponseEntity<Void> delete(@PathVariable Long id){
            this.userService.delete(id);
            return ResponseEntity.noContent().build();
        }

}
