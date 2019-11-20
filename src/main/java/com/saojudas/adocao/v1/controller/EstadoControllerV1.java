package com.saojudas.adocao.v1.controller;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.EstadoV1;
import com.saojudas.adocao.v1.dto.EstadoDTOV1;
import com.saojudas.adocao.v1.service.EstadoServiceV1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/estado")
@CrossOrigin("*")
@Api(value = "estados")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
}
)
public class EstadoControllerV1 {

    private final EstadoServiceV1 estadoService;

    @ApiOperation(value = "Buscar uma estado pelo seu id", response = EstadoDTOV1.class)
    @RequestMapping(value = "/get/estado/id/{idEstado}", method = RequestMethod.GET, produces = "application/json")
    public EstadoDTOV1 buscarEstadoPorId(@PathVariable Integer idEstado) throws DatabaseException {
        EstadoDTOV1 estadoDTOV1 = estadoService.buscarEstadoPorId(idEstado);
        return estadoDTOV1;
    }

    @ApiOperation(value = "Retornar todas as estados")
    @RequestMapping(value = "/get/estados", method = RequestMethod.GET, produces = "application/json")
    public List<EstadoDTOV1> buscarEstados() throws ResourceNotFoundException, DatabaseException {
        List<EstadoDTOV1> listaEstadosDTOV1 = estadoService.buscarEstados();
        return listaEstadosDTOV1;
    }

    @ApiOperation(value = "Adicionar estado", response = EstadoDTOV1.class)
    @RequestMapping(value = "/add/estado", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<EstadoDTOV1> addEstado(@RequestParam("nomeEstado") String nomeEstado) throws DatabaseException {
        EstadoDTOV1 estadoDTO = estadoService.addEstado(EstadoV1.builder()
                .nomeEstado(nomeEstado)
                .build());
        return ResponseEntity.ok(estadoDTO);
    }


    @ApiOperation(value = "deletar uma estado pelo seu id", response = EstadoDTOV1.class)
    @RequestMapping(value = "/deletar/estado/id/{idEstado}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity removerEstadoPorId(@PathVariable Integer idEstado) throws DatabaseException {
        estadoService.removerEstadoPorId(idEstado);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "atualizar uma estado pelo seu id", response = EstadoDTOV1.class)
    @RequestMapping(value = "/atualizar/estado/id/{idEstado}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addEstado(@PathVariable("idEstado") Integer idEstado,
                                    @RequestParam(name="nomeEstado", required = false) Optional<String> nomeEstado) throws DatabaseException {

        EstadoDTOV1 estado = estadoService.buscarEstadoPorId(idEstado);

        nomeEstado.ifPresent(estado::setNomeEstado);

        estadoService.updateEstado(estado);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}