package com.idho.soal_programming.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MataKuliahSiswaDto2 {

    @JsonProperty("id_detail")
    private String idDetail;

    @JsonProperty("id_matakuliah")
    private String matakuliah;

}
