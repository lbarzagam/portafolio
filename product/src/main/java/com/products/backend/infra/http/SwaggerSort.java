package com.products.backend.infra.http;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SwaggerSort {

    @ApiParam(hidden = true)
    @ApiModelProperty(value = "Tiene valor true si la lista está ordenada")
    private boolean sorted;

    @ApiParam(hidden = true)
    @ApiModelProperty(value = "Tiene valor true si la lista no está ordenada")
    private boolean unsorted;

    @ApiParam(hidden = true)
    @ApiModelProperty(value = "Tiene valor true si en la petición se solicitó recuperar la lista sin especificar el orden")
    private boolean empty;

    public static SwaggerSort toSwaggerSort(Sort sort) {
        return new SwaggerSort(sort.isSorted(), sort.isUnsorted(), sort.isEmpty());
    }
}

