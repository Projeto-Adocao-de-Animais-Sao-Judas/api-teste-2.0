package com.saojudas.adocao.v1.controller;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.CidadeV1;
import com.saojudas.adocao.v1.dto.CidadeDTOV1;
import com.saojudas.adocao.v1.service.CidadeServiceV1;
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
@RequestMapping(path = "/cidade")
@CrossOrigin("*")
@Api(value = "cidades")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
}
)
public class CidadeControllerV1 {

    private final CidadeServiceV1 cidadeService;

    @ApiOperation(value = "Buscar uma cidade pelo seu id", response = CidadeDTOV1.class)
    @RequestMapping(value = "/get/cidade/id/{idCidade}", method = RequestMethod.GET, produces = "application/json")
    public CidadeDTOV1 buscarCidadePorId(@PathVariable Integer idCidade) throws DatabaseException {
        CidadeDTOV1 cidadeDTOV1 = cidadeService.buscarCidadePorId(idCidade);
        return cidadeDTOV1;
    }

    @ApiOperation(value = "Retornar todas as cidades")
    @RequestMapping(value = "/get/cidades", method = RequestMethod.GET, produces = "application/json")
    public List<CidadeDTOV1> buscarCidades() throws ResourceNotFoundException, DatabaseException {
        List<CidadeDTOV1> listaCidadesDTOV1 = cidadeService.buscarCidades();
        return listaCidadesDTOV1;
    }

    @ApiOperation(value = "Adicionar cidade", response = CidadeDTOV1.class)
    @RequestMapping(value = "/add/cidade", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<CidadeDTOV1> addCidade(@RequestParam("nomeCidade") String nomeCidade) throws DatabaseException {
        CidadeDTOV1 cidadeDTO = cidadeService.addCidade(CidadeV1.builder()
                .nomeCidade(nomeCidade)
                .build());
        return ResponseEntity.ok(cidadeDTO);
    }


    @ApiOperation(value = "deletar uma cidade pelo seu id", response = CidadeDTOV1.class)
    @RequestMapping(value = "/deletar/cidade/id/{idCidade}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity removerCidadePorId(@PathVariable Integer idCidade) throws DatabaseException {
        cidadeService.removerCidadePorId(idCidade);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "atualizar uma cidade pelo seu id", response = CidadeDTOV1.class)
    @RequestMapping(value = "/atualizar/cidade/id/{idCidade}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addCidade(@PathVariable("idCidade") Integer idCidade,
                                    @RequestParam(name="nomeCidade", required = false) Optional<String> nomeCidade) throws DatabaseException {

        CidadeDTOV1 cidade = cidadeService.buscarCidadePorId(idCidade);

        nomeCidade.ifPresent(cidade::setNomeCidade);

        cidadeService.updateCidade(cidade);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}