package com.microservicios.wastemanagerservice.client;

import com.microservicios.wastemanagerservice.dto.WasteManagerAddressDtoResponse;
import com.microservicios.wastemanagerservice.dto.WasteManagerAddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "waste-manager-address")
public interface WasteManagerAddressClient {

    @RequestMapping(value = "/managerAddress/hello")
    String helloManagerAdress();

    @GetMapping(value = "/managerAddress/listWasteManagerAddress")
    List<WasteManagerAddressDtoResponse> listManagerAddress();

    @PostMapping(value = "/managerAddress/crearWasteManagerAddress")
    WasteManagerAddressDto crearManagerAddress(@RequestBody WasteManagerAddressDto wasteManagerAddressDto);

    @PutMapping(value = "/managerAddress/updateWasteManagerAddress")
    WasteManagerAddressDto updateManagerAddress(@RequestBody WasteManagerAddressDto wasteManagerAddressEntity);

    @DeleteMapping(value = "/managerAddress/deleteWasteManagerAddress/{id}")
    String deleteManagerAddress(@PathVariable Long id);
}
