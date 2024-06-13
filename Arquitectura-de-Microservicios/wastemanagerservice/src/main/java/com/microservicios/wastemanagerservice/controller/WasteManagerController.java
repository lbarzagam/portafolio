package com.microservicios.wastemanagerservice.controller;

import com.microservicios.wastemanagerservice.client.WasteManagerAddressClient;
import com.microservicios.wastemanagerservice.dto.WasteManagerAddressDto;
import com.microservicios.wastemanagerservice.dto.WasteManagerDto;
import com.microservicios.wastemanagerservice.dto.WasteManagerDtoResponse;
import com.microservicios.wastemanagerservice.service.WasteManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wasteManager")
@RequiredArgsConstructor
public class WasteManagerController {

    private final WasteManagerService wasteManagerService;
    private final WasteManagerAddressClient wasteManagerAddressClient;

    @GetMapping(value = "/hello")
    public ResponseEntity testHello() throws Exception {
        return wasteManagerService.sayHello();
    }

    @GetMapping(value = "/listWaste")
    public ResponseEntity listWaste(){
        return new ResponseEntity<>(wasteManagerService.listWaste(), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity createWaste(@RequestBody WasteManagerDto wasteManagerDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(wasteManagerService.crearWaste(wasteManagerDto), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateWaste(@RequestBody WasteManagerDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(wasteManagerService.updateWaste(dto), HttpStatus.OK);
    }

    @GetMapping(value = "/findById/{wasteManagerId}")
    public ResponseEntity findWasteById(@PathVariable(value = "wasteManagerId") Long wasteManagerId) {
        WasteManagerDtoResponse wasteManagerDtoResponse = new WasteManagerDtoResponse();
        wasteManagerDtoResponse.crearNuevo(wasteManagerService.findWasteById(wasteManagerId));

        return new ResponseEntity(wasteManagerDtoResponse, HttpStatus.OK);
    }


    /*********************************************************************************
     /*Manejando Crud WasteManagerAddressEntity*/
    @GetMapping(value = "/managerAddress/sayHello")
    public ResponseEntity sayHello() {
        return new ResponseEntity(wasteManagerAddressClient.helloManagerAdress(), HttpStatus.OK);
    }

    @GetMapping(value = "/managerAddress/list")
    public ResponseEntity listManagerAdress() {
        return new ResponseEntity(wasteManagerAddressClient.listManagerAddress(), HttpStatus.OK);
    }

    @PostMapping(value = "/managerAddress/crear")
    public ResponseEntity crearManagerAdress(@RequestBody WasteManagerAddressDto wasteManagerAddressDto) {
        return new ResponseEntity(wasteManagerAddressClient.crearManagerAddress(wasteManagerAddressDto), HttpStatus.OK);
    }

    @PutMapping(value = "/managerAddress/update")
    ResponseEntity updateManagerAdress(@RequestBody WasteManagerAddressDto wasteManagerAddressDtoUpdate) {
        return new ResponseEntity(wasteManagerAddressClient.updateManagerAddress(wasteManagerAddressDtoUpdate), HttpStatus.OK);
    }

    @DeleteMapping(value = "/managerAddress/delete/{id}")
    ResponseEntity deleteManagerAdress(@PathVariable Long id) {
        return new ResponseEntity(wasteManagerAddressClient.deleteManagerAddress(id), HttpStatus.OK);
    }
}
