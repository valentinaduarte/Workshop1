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
import com.riwi.librosya.api.dto.request.ReservationRequest;
import com.riwi.librosya.api.dto.response.ReservationResponse;
import com.riwi.librosya.infrastructure.services.ReservationService;
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
@RequestMapping(path = "/reservations")
@Data
@AllArgsConstructor
public class ReservationController {
   
    @Autowired
    private final ReservationService reservationService;

    /*crear */
        @PostMapping
        @Operation(summary = "Creacion de una reserva de un libro")
        @ApiResponse()
        public ResponseEntity<ReservationResponse> create(
            @Validated ReservationRequest request
        ){
            return ResponseEntity.ok(this.reservationService.create(request));
        }
    
    /* read */
        @GetMapping
        @Operation(summary = "Obtiene las reservaciones de forma paginada y organizada")
        public ResponseEntity<Page<ReservationResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestHeader(required = false)SortType sortType
        ){
            if (Objects.isNull(sortType)) {
                sortType = SortType.NONE;
            }
            
            return ResponseEntity.ok(this.reservationService.getAll(page, size, sortType));
        }

        @GetMapping(path = "/{id}")
        @Operation(summary = "Obtiene la reservacion por id")
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
        public ResponseEntity<ReservationResponse> getById(@PathVariable Long id){
            return ResponseEntity.ok(this.reservationService.getById(id));
        }
    
    /* update */
        @PutMapping(path="/{id}")
        @Operation(summary = "Actualiza una reservacion por id")
        @ApiResponse(
            responseCode = "400",
            description = "Cuando el id no es valido",
            content = {
                @Content(
                mediaType= "application/json",
                schema = @Schema(implementation = ErrorResponse.class))
            }
        )
        public ResponseEntity<ReservationResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody ReservationRequest request
        ){
            return ResponseEntity.ok(this.reservationService.update(request, id));
        }
    
    /* delete */
        @DeleteMapping(path = "/{id}")
        @Operation(summary = "Elimina las reservaciones por id")
        @ApiResponse(
            responseCode = "400",
            description = "cuando el id no es valido",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            }
        )
        public ResponseEntity<Void>delete(@PathVariable Long id){
            this.reservationService.delete(id);
            return ResponseEntity.noContent().build();
        }
}