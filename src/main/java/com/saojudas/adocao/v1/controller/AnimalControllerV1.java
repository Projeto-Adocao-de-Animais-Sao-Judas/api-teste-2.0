package com.saojudas.adocao.v1.controller;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.AnimalV1;
import com.saojudas.adocao.v1.dto.AnimalDTOV1;
import com.saojudas.adocao.v1.service.AnimalServiceV1;
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
@RequestMapping(path = "/animal")
@CrossOrigin("*")
@Api(value = "animais")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
}
)
public class AnimalControllerV1 {

    private final AnimalServiceV1 animalService;

    @ApiOperation(value = "Buscar uma animal pelo seu id", response = AnimalDTOV1.class)
    @RequestMapping(value = "/get/animal/id/{idAnimal}", method = RequestMethod.GET, produces = "application/json")
    public AnimalDTOV1 buscarAnimalPorId(@PathVariable Integer idAnimal) throws DatabaseException {
        AnimalDTOV1 animalDTOV1 = animalService.buscarAnimalPorId(idAnimal);
        return animalDTOV1;
    }

    @ApiOperation(value = "Retornar todas as animal")
    @RequestMapping(value = "/get/animais", method = RequestMethod.GET, produces = "application/json")
    public List<AnimalDTOV1> buscarAnimais() throws ResourceNotFoundException, DatabaseException {
        List<AnimalDTOV1> listaAnimaisDTOV1 = animalService.buscarAnimais();
        return listaAnimaisDTOV1;
    }

    @ApiOperation(value = "Adicionar animal", response = AnimalDTOV1.class)
    @RequestMapping(value = "/add/animal", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<AnimalDTOV1> addAnimal(@RequestParam("nomeAnimal") String nomeAnimal,
                                                 @RequestParam("sexoAnimal") String sexoAnimal,
                                                 @RequestParam("idadeAnimal") Integer idadeAnimal,
                                                 @RequestParam("tipoAnimal") String tipoAnimal,
                                                 @RequestParam("obsAnimal") String obsAnimal) throws DatabaseException {
        AnimalDTOV1 animalDTO = animalService.addAnimal(AnimalV1.builder()
                .nomeAnimal(nomeAnimal)
                .sexoAnimal(sexoAnimal)
                .idadeAnimal(idadeAnimal)
                .tipoAnimal(tipoAnimal)
                .obsAnimal(obsAnimal)
                .build());
        return ResponseEntity.ok(animalDTO);
    }


    @ApiOperation(value = "deletar uma animal pelo seu id", response = AnimalDTOV1.class)
    @RequestMapping(value = "/deletar/animal/id/{idAnimal}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity removerAnimalPorId(@PathVariable Integer idAnimal) throws DatabaseException {
        animalService.removerAnimalPorId(idAnimal);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "atualizar uma animal pelo seu id", response = AnimalDTOV1.class)
    @RequestMapping(value = "/atualizar/animal/id/{idAnimal}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addAnimal(@PathVariable("idAnimal") Integer idAnimal,
                                    @RequestParam(name="nomeAnimal", required = false) Optional<String> nomeAnimal,
                                    @RequestParam(name="sexoAnimal", required = false) Optional<String> sexoAnimal,
                                    @RequestParam(name="idadeAnimal", required = false) Optional<Integer> idadeAnimal,
                                    @RequestParam(name="tipoAnimal", required = false) Optional<String> tipoAnimal,
                                    @RequestParam(name="obsAnimal", required = false) Optional<String> obsAnimal) throws DatabaseException {

        AnimalDTOV1 animal = animalService.buscarAnimalPorId(idAnimal);

        nomeAnimal.ifPresent(animal::setNomeAnimal);
        sexoAnimal.ifPresent(animal::setSexoAnimal);
        idadeAnimal.ifPresent(animal::setIdadeAnimal);
        tipoAnimal.ifPresent(animal::setTipoAnimal);
        obsAnimal.ifPresent(animal::setObsAnimal);

        animalService.updateAnimal(animal);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
