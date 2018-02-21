package com.github.szdx.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown =  true)
public class TestData {

    private String foo;

    private String bar;
}
