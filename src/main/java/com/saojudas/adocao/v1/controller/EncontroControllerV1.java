package com.saojudas.adocao.v1.controller;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.EncontroV1;
import com.saojudas.adocao.v1.dto.EncontroDTOV1;
import com.saojudas.adocao.v1.service.EncontroServiceV1;
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
@RequestMapping(path = "/encontro")
@CrossOrigin("*")
@Api(value = "encontros")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
}
)
public class EncontroControllerV1 {

    private final EncontroServiceV1 encontroService;

    @ApiOperation(value = "Buscar uma encontro pelo seu id", response = EncontroDTOV1.class)
    @RequestMapping(value = "/get/encontro/id/{idEncontro}", method = RequestMethod.GET, produces = "application/json")
    public EncontroDTOV1 buscarEncontroPorId(@PathVariable Integer idEncontro) throws DatabaseException {
        EncontroDTOV1 encontroDTOV1 = encontroService.buscarEncontroPorId(idEncontro);
        return encontroDTOV1;
    }

    @ApiOperation(value = "Retornar todas as encontro")
    @RequestMapping(value = "/get/encontros", method = RequestMethod.GET, produces = "application/json")
    public List<EncontroDTOV1> buscarEncontros() throws ResourceNotFoundException, DatabaseException {
        List<EncontroDTOV1> listaEncontrosDTOV1 = encontroService.buscarEncontros();
        return listaEncontrosDTOV1;
    }


    @ApiOperation(value = "Adicionar encontro", response = EncontroDTOV1.class)
    @RequestMapping(value = "/add/encontro", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<EncontroDTOV1> addEncontro(@RequestParam("fkAnimalEncontroIdAnimal") Integer fkAnimalEncontroIdAnimal,
                                                     @RequestParam("fkPessoaEncontroIdPessoa") Integer fkPessoaEncontroIdPessoa) throws DatabaseException {
        EncontroDTOV1 novoEncontro = encontroService.addEncontro(EncontroV1.builder()
                .fkAnimalEncontroIdAnimal(fkAnimalEncontroIdAnimal)
                .fkPessoaEncontroIdPessoa(fkPessoaEncontroIdPessoa)
                .build());
        return ResponseEntity.ok(novoEncontro);
    }


    @ApiOperation(value = "deletar uma encontro pelo seu id", response = EncontroDTOV1.class)
    @RequestMapping(value = "/deletar/encontro/id/{idEncontro}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity removerEncontroPorId(@PathVariable Integer idEncontro) throws DatabaseException {
        encontroService.removerEncontroPorId(idEncontro);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "atualizar uma encontro pelo seu id", response = EncontroDTOV1.class)
    @RequestMapping(value = "/atualizar/encontro/id/{idEncontro}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addEncontro(@PathVariable("idEncontro") Integer idEncontro,
                                      @RequestParam(name = "fkAnimalEncontroIdAnimal", required = false) Optional<Integer> fkAnimalEncontroIdAnimal,
                                      @RequestParam(name = "fkPessoaEncontroIdPessoa", required = false) Optional<Integer> fkPessoaEncontroIdPessoa) throws DatabaseException {
        EncontroDTOV1 encontro = encontroService.buscarEncontroPorId(idEncontro);

        fkAnimalEncontroIdAnimal.ifPresent(encontro::setFkAnimalEncontroIdAnimal);
        fkPessoaEncontroIdPessoa.ifPresent(encontro::setFkPessoaEncontroIdPessoa);

        encontroService.updateEncontro(encontro);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
