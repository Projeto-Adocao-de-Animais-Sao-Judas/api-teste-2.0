package com.saojudas.adocao.v1.controller;


import com.saojudas.adocao.exceptions.DatabaseTimeoutException;
import com.saojudas.adocao.v1.domain.PessoaV1;
import com.saojudas.adocao.v1.dto.PessoaDTOV1;
import com.saojudas.adocao.v1.service.PessoaServiceV1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pessoa")
@CrossOrigin("*")
@Api(value = "pessoas")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved source"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
}
)
public class PessoaControllerV1 {

    private final PessoaServiceV1 pessoaService;

    @ApiOperation(value = "Buscar uma pessoa pelo seu id", response = PessoaDTOV1.class)
    @RequestMapping(value = "/get/pessoa/id/{id}", method = RequestMethod.GET, produces = "application/json")
    public PessoaDTOV1 buscarPessoaPorId(@PathVariable Integer id) throws DatabaseTimeoutException {
        PessoaDTOV1 pessoa = pessoaService.buscarPessoaPorId(id);
        return pessoa;
    }

    @ApiOperation(value = "Retornar todas as pessoa")
    @RequestMapping(value = "/get/pessoas", method = RequestMethod.GET, produces = "application/json")
    public List<PessoaDTOV1> buscarPessoas() throws DatabaseTimeoutException {
        List<PessoaDTOV1> listaPessoasDTOV1 = pessoaService.buscarPessoas();
        return listaPessoasDTOV1;
    }

    @ApiOperation(value = "Adicionar pessoa", response = PessoaDTOV1.class)
    @RequestMapping(value = "/add/pessoa", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<PessoaDTOV1> addPessoa(@RequestParam("endereco") String enderecoPessoa,
                                                         @RequestParam("complemento") String complementoPessoa,
                                                         @RequestParam("numero") String numeroPessoa,
                                                         @RequestParam("cep") String cepPessoa,
                                                         @RequestParam("tel") String telPessoa,
                                                         @RequestParam("cpf") String cpfPessoa) throws DatabaseTimeoutException {
        PessoaDTOV1 novapessoa = pessoaService.addPessoa(PessoaV1.builder()
                .endereco(enderecoPessoa)
                .complemento(complementoPessoa)
                .numero(numeroPessoa)
                .cep(cepPessoa)
                .tel(telPessoa)
                .cpf(cpfPessoa)
                .build());
        return ResponseEntity.ok(novapessoa);
    }


    @ApiOperation(value = "deletar uma pessoa pelo seu id", response = PessoaDTOV1.class)
    @RequestMapping(value = "/deletar/pessoa/id/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> removerPessoaPorId(@PathVariable Integer id) throws DatabaseTimeoutException {
        pessoaService.removerPessoaPorId(id);
        return ResponseEntity.ok("Pessoa removida");
    }


    @ApiOperation(value = "atualizar uma pessoa pelo seu id", response = PessoaDTOV1.class)
    @RequestMapping(value = "/atualizar/pessoa/id/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> addPessoa(@RequestParam("id") Integer idPessoa,
                                                 @RequestParam("endereco") String enderecoPessoa,
                                                 @RequestParam("complemento") String complementoPessoa,
                                                 @RequestParam("numero") String numeroPessoa,
                                                 @RequestParam("cep") String cepPessoa,
                                                 @RequestParam("tel") String telPessoa,
                                                 @RequestParam("cpf") String cpfPessoa) throws DatabaseTimeoutException {
        PessoaV1 pessoa = pessoaService.obterPessoa(idPessoa);

        if (!enderecoPessoa.isEmpty()) {
            pessoa.setEndereco(enderecoPessoa);
        }
        if (!complementoPessoa.isEmpty()) {
            pessoa.setComplemento(complementoPessoa);
        }
        if (!numeroPessoa.isEmpty()) {
            pessoa.setNumero(numeroPessoa);
        }
        if (!cepPessoa.isEmpty()) {
            pessoa.setNumero(cepPessoa);
        }
        if (!telPessoa.isEmpty()) {
            pessoa.setTel(telPessoa);
        }
        if (!cpfPessoa.isEmpty()) {
            pessoa.setCpf(cpfPessoa);
        }
        return ResponseEntity.ok("Pessoa atualizada com sucesso");
    }






}


//
//
//
//
//    @ApiOperation(value = "Find a chapter by your novelId and number", response = ChapterDTOV1.class)
//    @RequestMapping(value = "/get/chapter/novelId/{novelId}/{chapterNumber}", method = RequestMethod.GET, produces = "application/json")
//    public ChapterDTOV1 getChapterByNovelIdAndNumber(@PathVariable Integer novelId, @PathVariable Integer chapterNumber) {
//        Chapter chapter = chapterService.getChapterByNovelIdAndNumber(novelId, chapterNumber);
//        ChapterDTOV1 chapterDTOV1 = modelMapper.map(chapter, ChapterDTOV1.class);
//        return chapterDTOV1;
//    }
//
//    @ApiOperation(value = "Get chapters count in repository", response = CountDTOV1.class)
//    @RequestMapping(value = "/get/count", method = RequestMethod.GET, produces = "application/json")
//    public CountDTOV1 getCountNovel() throws DatabaseTimeoutException {
//        Long count = chapterService.getAllChaptersCount();
//        return new CountDTOV1(count);
//    }
//
//    @ApiOperation(value = "Get chapters count by novelId in repository", response = CountDTOV1.class)
//    @RequestMapping(value = "/get/count/{novelId}", method = RequestMethod.GET, produces = "application/json")
//    public CountDTOV1 getChapterCountByNovelId(@PathVariable Integer novelId) throws DatabaseTimeoutException {
//        Long count = chapterService.getChapterCountByNovelId(novelId);
//        return new CountDTOV1(count);
//    }

    /*@ApiOperation(value = "Find a chapter by your novel id and chapter number", response = ChapterDTOV1.class)
    @RequestMapping(value = "/get/novelId/{novelId}/number/{number}", method = RequestMethod.GET, produces = "application/json")
    public ChapterDTOV1 getNovelById(@PathVariable Integer novelId, Integer number) {
        Chapter chapter = chapterService.getChapterByNovelIdAndNumber(novelId, number);
        ChapterDTOV1 chapterDTOV1 = modelMapper.map(chapter, ChapterDTOV1.class);
        return chapterDTOV1;
    }*/
