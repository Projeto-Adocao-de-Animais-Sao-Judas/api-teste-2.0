package com.saojudas.adocao.v1.controller;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.FotoAnimalV1;
import com.saojudas.adocao.v1.dto.FotoAnimalDTOV1;
import com.saojudas.adocao.v1.service.FotoAnimalServiceV1;
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
@RequestMapping(path = "/fotoAnimal")
@CrossOrigin("*")
@Api(value = "fotoAnimais")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
}
)
public class FotoAnimalControllerV1 {

    private final FotoAnimalServiceV1 fotoAnimalService;

    @ApiOperation(value = "Buscar uma fotoAnimal pelo seu id", response = FotoAnimalDTOV1.class)
    @RequestMapping(value = "/get/fotoAnimal/id/{idFotoAnimal}", method = RequestMethod.GET, produces = "application/json")
    public FotoAnimalDTOV1 buscarFotoAnimalPorId(@PathVariable Integer idFotoAnimal) throws DatabaseException {
        FotoAnimalDTOV1 fotoAnimalDTOV1 = fotoAnimalService.buscarFotoAnimalPorId(idFotoAnimal);
        return fotoAnimalDTOV1;
    }

    @ApiOperation(value = "Retornar todas as fotoAnimal")
    @RequestMapping(value = "/get/fotoAnimais", method = RequestMethod.GET, produces = "application/json")
    public List<FotoAnimalDTOV1> buscarFotoAnimais() throws ResourceNotFoundException, DatabaseException {
        List<FotoAnimalDTOV1> listaFotoAnimaisDTOV1 = fotoAnimalService.buscarFotoAnimais();
        return listaFotoAnimaisDTOV1;
    }


    @ApiOperation(value = "Adicionar fotoAnimal", response = FotoAnimalDTOV1.class)
    @RequestMapping(value = "/add/fotoAnimal", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<FotoAnimalDTOV1> addFotoAnimal(@RequestParam("fkAnimalFotoAnimalIdAnimal") Integer fkAnimalFotoAnimalIdAnimal,
                                                         @RequestParam("descricaoFotoAnimal") String descricaoFotoAnimal,
                                                         @RequestParam("imagemUrl") String imagemUrl) throws DatabaseException {
        FotoAnimalDTOV1 fotoAnimalDTO = fotoAnimalService.addFotoAnimal(FotoAnimalV1.builder()
                .fkAnimalFotoAnimalIdAnimal(fkAnimalFotoAnimalIdAnimal)
                .descricaoFotoAnimal(descricaoFotoAnimal)
                .imagemUrl(imagemUrl)
                .build());
        return ResponseEntity.ok(fotoAnimalDTO);
    }


    @ApiOperation(value = "deletar uma fotoAnimal pelo seu id", response = FotoAnimalDTOV1.class)
    @RequestMapping(value = "/deletar/fotoAnimal/id/{idFotoAnimal}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity removerFotoAnimalPorId(@PathVariable Integer idFotoAnimal) throws DatabaseException {
        fotoAnimalService.removerFotoAnimalPorId(idFotoAnimal);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "atualizar uma fotoAnimal pelo seu id", response = FotoAnimalDTOV1.class)
    @RequestMapping(value = "/atualizar/fotoAnimal/id/{idFotoAnimal}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addFotoAnimal(@PathVariable("idFotoAnimal") Integer idFotoAnimal,
                                        @RequestParam(name = "fkAnimalFotoAnimalIdAnimal", required = false) Optional<Integer> fkAnimalFotoAnimalIdAnimal,
                                        @RequestParam(name = "descricaoFotoAnimal", required = false) Optional<String> descricaoFotoAnimal,
                                        @RequestParam(name = "imagemUrl", required = false) Optional<String> imagemUrl) throws DatabaseException {

        FotoAnimalDTOV1 fotoAnimal = fotoAnimalService.buscarFotoAnimalPorId(idFotoAnimal);

        fkAnimalFotoAnimalIdAnimal.ifPresent(fotoAnimal::setFkAnimalFotoAnimalIdAnimal);
        descricaoFotoAnimal.ifPresent(fotoAnimal::setDescricaoFotoAnimal);
        imagemUrl.ifPresent(fotoAnimal::setImagemUrl);

        fotoAnimalService.updateFotoAnimal(fotoAnimal);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}