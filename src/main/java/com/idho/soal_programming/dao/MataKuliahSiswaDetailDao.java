package com.idho.soal_programming.dao;

import com.idho.soal_programming.dto.Soal2Dto;
import com.idho.soal_programming.entity.MataKuliahSiswaDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MataKuliahSiswaDetailDao extends JpaRepository<MataKuliahSiswaDetail, String> {
    Optional<MataKuliahSiswaDetail> findByMahasiswaNimAndMataKuliahId(String nim, String matkul);
    @Query("SELECT d.mataKuliah.namaMatkul AS mataKuliah, COUNT(d.mahasiswa) AS totalSiswa FROM MataKuliahSiswaDetail d GROUP BY d.mataKuliah")
    List<Soal2Dto>  findBySiswaCount();
}
