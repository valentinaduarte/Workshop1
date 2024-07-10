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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.librosya.api.dto.errors.ErrorResponse;
import com.riwi.librosya.api.dto.request.LoanRequest;
import com.riwi.librosya.api.dto.response.LoanResponse;
import com.riwi.librosya.infrastructure.services.LoanService;
import com.riwi.librosya.util.enums.SortType;
import com.riwi.librosya.util.messages.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping(path = "/loans")
@Data
@AllArgsConstructor
public class LoanController {
    
    @Autowired
    private final LoanService loanService;

    /* Crear */
        @PostMapping
        @Operation(summary = "Crear un prestamo de un libro")
        @ApiResponse()
        public ResponseEntity<LoanResponse> create(
            @Validated LoanRequest request
        ){
            return ResponseEntity.ok(this.loanService.create(request));
        }
    /* Read */
        @GetMapping
        @Operation(summary = "Obtiene los prestamos de forma paginada y organizada por el nombre")
        public ResponseEntity<Page<LoanResponse>>getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestHeader(required = false)SortType sortType
        ){
            if (Objects.isNull(sortType)) {
                sortType = SortType.NONE;
            }
            return ResponseEntity.ok(this.loanService.getAll(page, size, sortType));
        }

        @GetMapping(path = "/{id}")
        @Operation(summary = "Obtiene el prestamo por id")
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
        public ResponseEntity<LoanResponse>getById(@PathVariable Long id){
            return ResponseEntity.ok(this.loanService.getById(id));
        }

    /* update */
        @PutMapping(path = "/{id}")
        @Operation(summary = "Actualiza el prestamo por id")
        @ApiResponse(
            responseCode = "400", 
            description = "Cuando el id no es valido", 
            content = { 
                @Content(
                    mediaType = "application/json", 
                    schema = @Schema(implementation = ErrorResponse.class))
                }
        )
        public ResponseEntity<LoanResponse> update(
          @PathVariable Long id,
          @Validated @RequestBody LoanRequest request  
        ){
            return ResponseEntity.ok(this.loanService.update(request, id));
        }
    /* delete */
        @DeleteMapping(path = "/{id}")
        @Operation(summary = "Elimina el prestamo por id")
        @ApiResponse(
            responseCode = "400", 
            description = "Cuando el id no es valido", 
            content = { 
                @Content(
                    mediaType = "application/json", 
                    schema = @Schema(implementation = ErrorResponse.class))
                }
        )
        public ResponseEntity<Void>delete(@PathVariable Long id){
            this.loanService.delete(id);
            return ResponseEntity.noContent().build();
        }
}