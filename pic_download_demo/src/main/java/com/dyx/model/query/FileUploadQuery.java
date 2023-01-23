package com.dyx.model.query;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author Davison
 * @description FileUploadQuery
 * @date 2023/1/23 10:56
 */
@Data
@Validated
public class FileUploadQuery {
    @NotBlank(message = "imageName can't be blank")
    private String imageName;
    @NotBlank(message = "imageData can't be blank")
    private String imageData;
}
