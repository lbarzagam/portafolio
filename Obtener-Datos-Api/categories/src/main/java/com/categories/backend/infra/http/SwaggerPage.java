package com.categories.backend.infra.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SwaggerPage<T> {

    @ApiModelProperty(value = "Lista de registros recuperados")
    private List<T> content;

    @ApiModelProperty(value = "Tiene valor true si no se recuperó ningún registro")
    private boolean empty;

    @ApiModelProperty(value = "Tiene valor true si la página recuperada es la primera")
    private boolean first;

    @ApiModelProperty(value = "Tiene valor true si la página recuperada es la última")
    private boolean last;

    @ApiModelProperty(value = "Página recuperada (0..N)")
    private Integer number;

    @ApiModelProperty(value = "Cantidad de elementos recuperados")
    private Integer numberOfElements;

    @ApiModelProperty(value = "Configuración de paginación de la petición")
    private SwaggerPageable pageable;

    @ApiModelProperty(value = "Número de registros recuperados por página")
    private Integer size;

    @ApiModelProperty(value = "Configuración de ordenamiento de la lista")
    private SwaggerSort sort;

    @ApiModelProperty(value = "Total de registros de la tabla")
    private Long totalElements;

    @ApiModelProperty(value = "Total de páginas de la tabla")
    private Integer totalPages;

    public static SwaggerPage toSwaggerPage(Page page) {
        return new SwaggerPage(page.getContent(),
                page.isEmpty(), page.isFirst(), page.isLast(),
                page.getNumber(), page.getNumberOfElements(),
                SwaggerPageable.toSwaggerPageable(page.getPageable()),
                page.getSize(), SwaggerSort.toSwaggerSort(page.getSort()),
                page.getTotalElements(), page.getTotalPages());
    }
}

