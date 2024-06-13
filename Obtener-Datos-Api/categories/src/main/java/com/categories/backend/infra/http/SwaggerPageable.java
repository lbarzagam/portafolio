package com.categories.backend.infra.http;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SwaggerPageable {

    @ApiParam(value = "Página que se desea recuperar (0..N)", example = "0")
    @ApiModelProperty(hidden = true)
    private Integer page;

    @ApiParam(value = "Número de registros a obtener por página", example = "10")
    @ApiModelProperty(hidden = true)
    private Integer size;

    @ApiParam(value = "Configuración de ordenamiento de la lista. Formato: campo,criterio. El criterio puede ser asc o desc. Ejemplo: tenant,desc")
    @ApiModelProperty(hidden = true)
    private String sort;

    @ApiParam(hidden = true)
    @ApiModelProperty(value = "Configuración de ordenamiento de la lista")
    private SwaggerSort sortObj;

    @ApiParam(hidden = true)
    @ApiModelProperty(value = "Página recuperada (0..N)")
    private Integer pageNumber;

    @ApiParam(hidden = true)
    @ApiModelProperty(value = "Número de registros recuperados por página")
    private Integer pageSize;

    @ApiParam(hidden = true)
    @ApiModelProperty(value = "Posición del primer registro obtenido")
    private Long offset;

    @ApiParam(hidden = true)
    @ApiModelProperty(value = "Tiene valor true si el resultado no está paginado")
    private boolean unpaged;

    @ApiParam(hidden = true)
    @ApiModelProperty(value = "Tiene valor true si el resultado está paginado")
    private boolean paged;

    public static SwaggerPageable toSwaggerPageable(Pageable pageable) {
        int pageNumber = pageable.isPaged() ? pageable.getPageNumber() : 0;
        int pageSize = pageable.isPaged() ? pageable.getPageSize() : Integer.MAX_VALUE;
        long pageOffset = pageable.isPaged() ? pageable.getOffset() : 0;
        return new SwaggerPageable(pageNumber, pageSize,
                pageable.getSort().toString(), SwaggerSort.toSwaggerSort(pageable.getSort()), pageNumber,
                pageSize, pageOffset, pageable.isUnpaged(),
                pageable.isPaged());
    }
}
