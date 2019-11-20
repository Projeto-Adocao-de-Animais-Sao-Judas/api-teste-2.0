package com.saojudas.adocao.v1.controller;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.FotoPessoaV1;
import com.saojudas.adocao.v1.dto.FotoPessoaDTOV1;
import com.saojudas.adocao.v1.service.FotoPessoaServiceV1;
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
@RequestMapping(path = "/fotoPessoa")
@CrossOrigin("*")
@Api(value = "fotoPessoas")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
}
)
public class FotoPessoaControllerV1 {

    private final FotoPessoaServiceV1 fotoPessoaService;

    @ApiOperation(value = "Buscar uma fotoPessoa pelo seu id", response = FotoPessoaDTOV1.class)
    @RequestMapping(value = "/get/fotoPessoa/id/{idFotoPessoa}", method = RequestMethod.GET, produces = "application/json")
    public FotoPessoaDTOV1 buscarFotoPessoaPorId(@PathVariable Integer idFotoPessoa) throws DatabaseException {
        FotoPessoaDTOV1 fotoPessoaDTOV1 = fotoPessoaService.buscarFotoPessoaPorId(idFotoPessoa);
        return fotoPessoaDTOV1;
    }

    @ApiOperation(value = "Retornar todas as fotoPessoa")
    @RequestMapping(value = "/get/fotoPessoas", method = RequestMethod.GET, produces = "application/json")
    public List<FotoPessoaDTOV1> buscarFotoPessoas() throws ResourceNotFoundException, DatabaseException {
        List<FotoPessoaDTOV1> listaFotoPessoasDTOV1 = fotoPessoaService.buscarFotoPessoas();
        return listaFotoPessoasDTOV1;
    }


    @ApiOperation(value = "Adicionar fotoPessoa", response = FotoPessoaDTOV1.class)
    @RequestMapping(value = "/add/fotoPessoa", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<FotoPessoaDTOV1> addFotoPessoa(@RequestParam("fkPessoaFotoPessoaIdPessoa") Integer fkPessoaFotoPessoaIdPessoa,
                                                         @RequestParam("descricaoImagem") String descricaoImagem,
                                                         @RequestParam("imagemUrl") String imagemUrl) throws DatabaseException {
        FotoPessoaDTOV1 fotoPessoaDTO = fotoPessoaService.addFotoPessoa(FotoPessoaV1.builder()
                .fkPessoaFotoPessoaIdPessoa(fkPessoaFotoPessoaIdPessoa)
                .descricaoImagem(descricaoImagem)
                .imagemUrl(imagemUrl)
                .build());
        return ResponseEntity.ok(fotoPessoaDTO);
    }


    @ApiOperation(value = "deletar uma fotoPessoa pelo seu id", response = FotoPessoaDTOV1.class)
    @RequestMapping(value = "/deletar/fotoPessoa/id/{idFotoPessoa}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity removerFotoPessoaPorId(@PathVariable Integer idFotoPessoa) throws DatabaseException {
        fotoPessoaService.removerFotoPessoaPorId(idFotoPessoa);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "atualizar uma fotoPessoa pelo seu id", response = FotoPessoaDTOV1.class)
    @RequestMapping(value = "/atualizar/fotoPessoa/id/{idFotoPessoa}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addFotoPessoa(@PathVariable("idFotoPessoa") Integer idFotoPessoa,
                                        @RequestParam(name = "fkPessoaFotoPessoaIdPessoa", required = false) Optional<Integer> fkPessoaFotoPessoaIdPessoa,
                                        @RequestParam(name = "descricaoImagem", required = false) Optional<String> descricaoImagem,
                                        @RequestParam(name = "imagemUrl", required = false) Optional<String> imagemUrl) throws DatabaseException {

        FotoPessoaDTOV1 fotoPessoa = fotoPessoaService.buscarFotoPessoaPorId(idFotoPessoa);

        fkPessoaFotoPessoaIdPessoa.ifPresent(fotoPessoa::setFkPessoaFotoPessoaIdPessoa);
        descricaoImagem.ifPresent(fotoPessoa::setDescricaoImagem);
        imagemUrl.ifPresent(fotoPessoa::setImagemUrl);

        fotoPessoaService.updateFotoPessoa(fotoPessoa);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}