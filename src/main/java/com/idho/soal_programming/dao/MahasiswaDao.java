package com.idho.soal_programming.dao;

import com.idho.soal_programming.dto.Soal3Dto;
import com.idho.soal_programming.entity.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MahasiswaDao extends JpaRepository<Mahasiswa, String> {

    Optional<Mahasiswa> findByNim(String nim);

    @Query("SELECT m.jurusan.namaJurusan AS jurusan, COUNT(m.nim) AS totalSiswa FROM Mahasiswa m GROUP BY m.jurusan")
    List<Soal3Dto> findBySiswaCount();
}
