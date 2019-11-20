package com.saojudas.adocao.v1.controller;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.TipoDocumentoV1;
import com.saojudas.adocao.v1.dto.TipoDocumentoDTOV1;
import com.saojudas.adocao.v1.service.TipoDocumentoServiceV1;
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
@RequestMapping(path = "/tipoDocumento")
@CrossOrigin("*")
@Api(value = "tipoDocumentos")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
}
)
public class TipoDocumentoControllerV1 {

    private final TipoDocumentoServiceV1 tipoDocumentoService;

    @ApiOperation(value = "Buscar uma tipoDocumento pelo seu id", response = TipoDocumentoDTOV1.class)
    @RequestMapping(value = "/get/tipoDocumento/id/{idTipoDocumento}", method = RequestMethod.GET, produces = "application/json")
    public TipoDocumentoDTOV1 buscarTipoDocumentoPorId(@PathVariable Integer idTipoDocumento) throws DatabaseException {
        TipoDocumentoDTOV1 tipoDocumentoDTOV1 = tipoDocumentoService.buscarTipoDocumentoPorId(idTipoDocumento);
        return tipoDocumentoDTOV1;
    }

    @ApiOperation(value = "Retornar todas as tipoDocumento")
    @RequestMapping(value = "/get/tipoDocumentos", method = RequestMethod.GET, produces = "application/json")
    public List<TipoDocumentoDTOV1> buscarTipoDocumentos() throws ResourceNotFoundException, DatabaseException {
        List<TipoDocumentoDTOV1> listaTipoDocumentosDTOV1 = tipoDocumentoService.buscarTipoDocumentos();
        return listaTipoDocumentosDTOV1;
    }

    @ApiOperation(value = "Adicionar tipoDocumento", response = TipoDocumentoDTOV1.class)
    @RequestMapping(value = "/add/tipoDocumento", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<TipoDocumentoDTOV1> addTipoDocumento(@RequestParam("fkPessoaTipoDocumentoIdPessoa") Integer fkPessoaTipoDocumentoIdPessoa,
                                                         @RequestParam("nomeDocumento") String nomeDocumento,
                                                         @RequestParam("numeroTipoDocumento") String numeroTipoDocumento) throws DatabaseException {
        TipoDocumentoDTOV1 tipoDocumentoDTO = tipoDocumentoService.addTipoDocumento(TipoDocumentoV1.builder()
                .fkPessoaTipoDocumentoIdPessoa(fkPessoaTipoDocumentoIdPessoa)
                .nomeDocumento(nomeDocumento)
                .numeroTipoDocumento(numeroTipoDocumento)
                .build());
        return ResponseEntity.ok(tipoDocumentoDTO);
    }


    @ApiOperation(value = "deletar uma tipoDocumento pelo seu id", response = TipoDocumentoDTOV1.class)
    @RequestMapping(value = "/deletar/tipoDocumento/id/{idTipoDocumento}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity removerTipoDocumentoPorId(@PathVariable Integer idTipoDocumento) throws DatabaseException {
        tipoDocumentoService.removerTipoDocumentoPorId(idTipoDocumento);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "atualizar uma tipoDocumento pelo seu id", response = TipoDocumentoDTOV1.class)
    @RequestMapping(value = "/atualizar/tipoDocumento/id/{idTipoDocumento}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addTipoDocumento(@PathVariable("idTipoDocumento") Integer idTipoDocumento,
                                           @RequestParam(name = "fkPessoaTipoDocumentoIdPessoa", required = false) Optional<Integer> fkPessoaTipoDocumentoIdPessoa,
                                           @RequestParam(name = "nomeDocumento", required = false) Optional<String> nomeDocumento,
                                           @RequestParam(name = "numeroTipoDocumento", required = false) Optional<String> numeroTipoDocumento) throws DatabaseException {

        TipoDocumentoDTOV1 tipoDocumento = tipoDocumentoService.buscarTipoDocumentoPorId(idTipoDocumento);

        fkPessoaTipoDocumentoIdPessoa.ifPresent(tipoDocumento::setFkPessoaTipoDocumentoIdPessoa);
        nomeDocumento.ifPresent(tipoDocumento::setNomeDocumento);
        numeroTipoDocumento.ifPresent(tipoDocumento::setNumeroTipoDocumento);

        tipoDocumentoService.updateTipoDocumento(tipoDocumento);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}