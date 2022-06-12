package com.idho.soal_programming.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data @Entity
public class MataKuliahSiswaDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_mahasiswa")
    private Mahasiswa mahasiswa;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_matakuliah")
    private MataKuliah mataKuliah;
}
