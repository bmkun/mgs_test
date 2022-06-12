package com.idho.soal_programming.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MahasiswaMatkulDto {

    @JsonProperty("Nama_mahasiswa")
    private String namaSiswa;

    @JsonProperty("Mata_Kuliah")
    private List<String> mataKuliah = new ArrayList<>();
}
