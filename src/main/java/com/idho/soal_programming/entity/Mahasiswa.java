package com.idho.soal_programming.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Mahasiswa {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    private String nim;

    private String namaSiswa;

    @ManyToOne
    @JoinColumn(name = "id_jurusan", nullable = false)
    private Jurusan jurusan;

    @ManyToOne
    @JoinColumn(name = "id_fakultas", nullable = false)
    private Fakultas fakultas;

    @JsonManagedReference
    @OneToMany(mappedBy = "mahasiswa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MataKuliahSiswaDetail> mataKuliahSiswaDetails = new ArrayList<>();

}
