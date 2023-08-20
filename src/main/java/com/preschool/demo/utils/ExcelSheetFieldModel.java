package com.preschool.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelSheetFieldModel implements Serializable {
    private String key;
    private String title;
    private Integer order;
    private boolean required;
    //private FieldType fieldType;

}
