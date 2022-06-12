package com.idho.soal_programming.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MahasiswaDto {

    private String nim;
    private String nama;

    @JsonProperty("id_fakultas")
    private String fakultas;

    @JsonProperty("id_jurusan")
    private String jurusan;
}
