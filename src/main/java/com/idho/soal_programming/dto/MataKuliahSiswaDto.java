package com.idho.soal_programming.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MataKuliahSiswaDto {

    private String nim;

    @JsonProperty("id_matakuliah")
    private String matakuliah;
}
