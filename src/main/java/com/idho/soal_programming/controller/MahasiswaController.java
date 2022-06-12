package com.idho.soal_programming.controller;

import com.idho.soal_programming.dao.FakultasDao;
import com.idho.soal_programming.dao.JurusanDao;
import com.idho.soal_programming.dao.MahasiswaDao;
import com.idho.soal_programming.dao.MataKuliahSiswaDetailDao;
import com.idho.soal_programming.dto.MahasiswaDto;
import com.idho.soal_programming.dto.MahasiswaDto2;
import com.idho.soal_programming.dto.MahasiswaMatkulDto;
import com.idho.soal_programming.dto.Response;
import com.idho.soal_programming.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/mahasiswa")
public class MahasiswaController {

    @Autowired
    private MahasiswaDao mahasiswaDao;

    @Autowired
    private FakultasDao fakultasDao;

    @Autowired
    private JurusanDao jurusanDao;

    @Autowired
    private MataKuliahSiswaDetailDao mataKuliahSiswaDetailDao;

    @GetMapping("/findAll")
    public Response findAll() {
        Response response = new Response();
        response.setMessage("Sukses");

        List<MahasiswaDto2> list = new ArrayList<>();
        mahasiswaDao.findAll().forEach(data -> {
            MahasiswaDto2 dto = new MahasiswaDto2();
            dto.setNim(data.getNim());
            dto.setNama(data.getNamaSiswa());
            dto.setFakultas(data.getFakultas().getNamaFakultas());
            dto.setJurusan(data.getJurusan().getNamaJurusan());

            list.add(dto);
        });

        response.setData(list);
        return response;
    }

    @PostMapping("/save")
    public Response save(@RequestBody MahasiswaDto mahasiswaDto) {
        Response response = new Response();

        try {

            Optional<Mahasiswa> opMahasiswa = mahasiswaDao.findByNim(mahasiswaDto.getNim());

            if (!opMahasiswa.isPresent()) {
                Mahasiswa mahasiswa = new Mahasiswa();

                mahasiswa.setNim(mahasiswaDto.getNim());
                mahasiswa.setNamaSiswa(mahasiswaDto.getNama());

                Optional<Fakultas> fakultas = fakultasDao.findById(mahasiswaDto.getFakultas());
                if (fakultas.isPresent()) {
                    mahasiswa.setFakultas(fakultas.get());
                }

                Optional<Jurusan> jurusan = jurusanDao.findById(mahasiswaDto.getJurusan());
                if (jurusan.isPresent()) {
                    mahasiswa.setJurusan(jurusan.get());
                }
                mahasiswaDao.save(mahasiswa);

                response.setMessage("Berhasil disimpan.");
                response.setData(new ArrayList<>());
                return response;
            } else {
                response.setMessage("Gagal disimpan.");
                response.setData(new ArrayList<>());
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Gagal disimpan.");
            response.setData(new ArrayList<>());
            return response;
        }
    }

    @PostMapping("/update")
    public Response update(@RequestBody MahasiswaDto mahasiswaDto) {
        Response response = new Response();

        try {
            Optional<Mahasiswa> opMahasiswa = mahasiswaDao.findByNim(mahasiswaDto.getNim());

            if (opMahasiswa.isPresent()) {
                Mahasiswa mahasiswa = opMahasiswa.get();
                mahasiswa.setNim(mahasiswaDto.getNim());
                mahasiswa.setNamaSiswa(mahasiswaDto.getNama());

                Optional<Fakultas> fakultas = fakultasDao.findById(mahasiswaDto.getFakultas());
                if (fakultas.isPresent()) {
                    mahasiswa.setFakultas(fakultas.get());
                }

                Optional<Jurusan> jurusan = jurusanDao.findById(mahasiswaDto.getJurusan());
                if (jurusan.isPresent()) {
                    mahasiswa.setJurusan(jurusan.get());
                }

                mahasiswaDao.save(mahasiswa);
                response.setMessage("Berhasil di update.");
                response.setData(new ArrayList<>());
                return response;
            } else {
                response.setMessage("Gagal Update");
                response.setData(new ArrayList<>());
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Gagal Update.");
            response.setData(new ArrayList<>());
            return response;
        }
    }

    @DeleteMapping("/delete")
    private Response delete(@RequestParam String id) {
        Response response = new Response();
        try {
            mahasiswaDao.deleteById(id);
            response.setMessage("Data mahasiswa berhasil di hapus");
            response.setData(new ArrayList<>());
            return response;
        } catch (Exception e) {
            response.setMessage("Data mahasiswa gagal di hapus");
            response.setData(new ArrayList<>());
            return response;
        }
    }

    @GetMapping("/soal1")
    public Response soal1() {
        Response response = new Response();
        List<MahasiswaMatkulDto> list = new ArrayList<>();

        for (Mahasiswa siswa : mahasiswaDao.findAll()) {
            List<String> listMatkul = new ArrayList<>();
            MahasiswaMatkulDto mahasiswaMatkulDto = new MahasiswaMatkulDto();

            mahasiswaMatkulDto.setNamaSiswa(siswa.getNamaSiswa());
            list.add(mahasiswaMatkulDto);

            for (MataKuliahSiswaDetail detail : siswa.getMataKuliahSiswaDetails()) {
                if (detail.getMahasiswa().getId().equals(siswa.getId())) {
                    listMatkul.add(detail.getMataKuliah().getNamaMatkul());
                }
            }
            mahasiswaMatkulDto.setMataKuliah(listMatkul);
        }

        response.setMessage("Sukses");
        response.setData(list);
        return response;
    }

    @GetMapping("/soal2")
    public Response soal2() {
        Response response = new Response();
        response.setMessage("Sukses");
        response.setData(mataKuliahSiswaDetailDao.findBySiswaCount());
        return response;
    }

    @GetMapping("/soal3")
    public Response soal3() {
        Response response = new Response();
        response.setMessage("Sukses");
        response.setData(mahasiswaDao.findBySiswaCount());
        return response;
    }
}
