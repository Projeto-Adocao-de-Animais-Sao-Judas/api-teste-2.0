package com.saojudas.adocao.v1.controller;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.PessoaV1;
import com.saojudas.adocao.v1.dto.PessoaDTOV1;
import com.saojudas.adocao.v1.service.PessoaServiceV1;
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
@RequestMapping(path = "/pessoa")
@CrossOrigin("*")
@Api(value = "pessoas")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
}
)
public class PessoaControllerV1 {

    private final PessoaServiceV1 pessoaService;

    @ApiOperation(value = "Buscar uma pessoa pelo seu id", response = PessoaDTOV1.class)
    @RequestMapping(value = "/get/pessoa/id/{idPessoa}", method = RequestMethod.GET, produces = "application/json")
    public PessoaDTOV1 buscarPessoaPorId(@PathVariable Integer idPessoa) throws DatabaseException {
        PessoaDTOV1 pessoaDTOV1 = pessoaService.buscarPessoaPorId(idPessoa);
        return pessoaDTOV1;
    }

    @ApiOperation(value = "Retornar todas as pessoa")
    @RequestMapping(value = "/get/pessoas", method = RequestMethod.GET, produces = "application/json")
    public List<PessoaDTOV1> buscarPessoas() throws ResourceNotFoundException, DatabaseException {
        List<PessoaDTOV1> listaPessoasDTOV1 = pessoaService.buscarPessoas();
        return listaPessoasDTOV1;
    }

    @ApiOperation(value = "Adicionar pessoa", response = PessoaDTOV1.class)
    @RequestMapping(value = "/add/pessoa", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<PessoaDTOV1> addPessoa(@RequestParam("fkPessoaTipoDocumentoIdTipoDocumento") Integer fkPessoaTipoDocumentoIdTipoDocumento,
                                                 @RequestParam("fkLoginPessoaIdLogin") Integer fkLoginPessoaIdLogin,
                                                 @RequestParam("fkEnderecoPessoaIdEndereco") Integer fkEnderecoPessoaIdEndereco,@RequestParam("telefonePessoa") String telefonePessoa,
                                                 @RequestParam("nomePessoa") String nomePessoa,
                                                 @RequestParam("emailPessoa") String emailPessoa,
                                                 @RequestParam("sexoPessoa") String sexoPessoa) throws DatabaseException {
        PessoaDTOV1 novapessoa = pessoaService.addPessoa(PessoaV1.builder()
                .fkPessoaTipoDocumentoIdTipoDocumento(fkPessoaTipoDocumentoIdTipoDocumento)
                .fkLoginPessoaIdLogin(fkLoginPessoaIdLogin)
                .fkEnderecoPessoaIdEndereco(fkEnderecoPessoaIdEndereco)
                .telefonePessoa(telefonePessoa)
                .nomePessoa(nomePessoa)
                .emailPessoa(emailPessoa)
                .sexoPessoa(sexoPessoa)
                .build());
        return ResponseEntity.ok(novapessoa);
    }


    @ApiOperation(value = "deletar uma pessoa pelo seu id", response = PessoaDTOV1.class)
    @RequestMapping(value = "/deletar/pessoa/id/{idPessoa}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity removerPessoaPorId(@PathVariable Integer idPessoa) throws DatabaseException {
        pessoaService.removerPessoaPorId(idPessoa);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "atualizar uma pessoa pelo seu id", response = PessoaDTOV1.class)
    @RequestMapping(value = "/atualizar/pessoa/id/{idPessoa}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addPessoa(@PathVariable("idPessoa") Integer idPessoa,
                                            @RequestParam(name="fkPessoaTipoDocumentoIdTipoDocumento", required = false) Optional<Integer> fkPessoaTipoDocumentoIdTipoDocumento,
                                            @RequestParam(name="fkLoginPessoaIdLogin", required = false) Optional<Integer> fkLoginPessoaIdLogin,
                                            @RequestParam(name="fkEnderecoPessoaIdEndereco", required = false) Optional<Integer> fkEnderecoPessoaIdEndereco,
                                            @RequestParam(name="telefonePessoa", required = false) Optional<String> telefonePessoa,
                                            @RequestParam(name="nomePessoa", required = false) Optional<String> nomePessoa,
                                            @RequestParam(name="emailPessoa", required = false) Optional<String> emailPessoa,
                                            @RequestParam(name="sexoPessoa", required = false) Optional<String> sexoPessoa) throws DatabaseException {
        PessoaDTOV1 pessoa = pessoaService.buscarPessoaPorId(idPessoa);

        telefonePessoa.ifPresent(pessoa::setTelefonePessoa);
        fkPessoaTipoDocumentoIdTipoDocumento.ifPresent(pessoa::setFkPessoaTipoDocumentoIdTipoDocumento);
        fkLoginPessoaIdLogin.ifPresent(pessoa::setFkLoginPessoaIdLogin);
        fkEnderecoPessoaIdEndereco.ifPresent(pessoa::setFkEnderecoPessoaIdEndereco);
        nomePessoa.ifPresent(pessoa::setNomePessoa);
        emailPessoa.ifPresent(pessoa::setEmailPessoa);
        sexoPessoa.ifPresent(pessoa::setSexoPessoa);

        pessoaService.updatePessoa(pessoa);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
