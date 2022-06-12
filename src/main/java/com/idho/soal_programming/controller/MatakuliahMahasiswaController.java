package com.idho.soal_programming.controller;

import com.idho.soal_programming.dao.MahasiswaDao;
import com.idho.soal_programming.dao.MataKuliahDao;
import com.idho.soal_programming.dao.MataKuliahSiswaDetailDao;
import com.idho.soal_programming.dto.MataKuliahSiswaDto;
import com.idho.soal_programming.dto.MataKuliahSiswaDto2;
import com.idho.soal_programming.dto.MatkulSiswaDetailDto;
import com.idho.soal_programming.dto.Response;
import com.idho.soal_programming.entity.Mahasiswa;
import com.idho.soal_programming.entity.MataKuliah;
import com.idho.soal_programming.entity.MataKuliahSiswaDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mata_kuliah_siswa")
public class MatakuliahMahasiswaController {

    @Autowired
    private MahasiswaDao mahasiswaDao;

    @Autowired
    private MataKuliahSiswaDetailDao matkulSiswaDetailDao;

    @Autowired
    private MataKuliahDao mataKuliahDao;

    @GetMapping("/findAll")
    public Response findAll() {
        List<MatkulSiswaDetailDto> list = new ArrayList<>();
        Response response = new Response();
        response.setMessage("Sukses");

        matkulSiswaDetailDao.findAll().forEach(data -> {
            MatkulSiswaDetailDto detailDto = new MatkulSiswaDetailDto();
            detailDto.setNim(data.getMahasiswa().getNim());
            detailDto.setNamaMahasiswa(data.getMahasiswa().getNamaSiswa());
            detailDto.setMataKuliah(data.getMataKuliah().getNamaMatkul());

            list.add(detailDto);
        });

        response.setData(list);
        return response;
    }

    @PostMapping("/save")
    public Response save(@RequestBody MataKuliahSiswaDto matkulSiswaDto) {
        Response response = new Response();
        try {
            MataKuliahSiswaDetail matkulSiswa = new MataKuliahSiswaDetail();

            Optional<MataKuliahSiswaDetail> matkulSiswaDtls = matkulSiswaDetailDao.findByMahasiswaNimAndMataKuliahId(matkulSiswaDto.getNim(), matkulSiswaDto.getMatakuliah());
            if (matkulSiswaDtls.isPresent()) {
                response.setMessage("Duplicate Data");
                response.setData(new ArrayList<>());
                return response;
            }

            Optional<Mahasiswa> mahasiswa = mahasiswaDao.findByNim(matkulSiswaDto.getNim());
            if (mahasiswa.isPresent()) {
                matkulSiswa.setMahasiswa(mahasiswa.get());
            }

            Optional<MataKuliah> mataKuliah = mataKuliahDao.findById(matkulSiswaDto.getMatakuliah());
            if (mataKuliah.isPresent()) {
                matkulSiswa.setMataKuliah(mataKuliah.get());
            }

            matkulSiswaDetailDao.save(matkulSiswa);
            response.setMessage("Data berhasil di simpan");
            response.setData(new ArrayList<>());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Data gagal di simpan");
            response.setData(new ArrayList<>());
            return response;
        }
    }

    @PostMapping("/update")
    public Response update(@RequestBody MataKuliahSiswaDto2 matkulSiswaDto) {
        Response response = new Response();
        try {

            Optional<MataKuliahSiswaDetail> opMatkulSiswa = matkulSiswaDetailDao.findById(matkulSiswaDto.getIdDetail());
            if (opMatkulSiswa.isPresent()) {
                MataKuliahSiswaDetail matkulSiswaDetail = opMatkulSiswa.get();

               // validasi cek duplicate
                Optional<MataKuliahSiswaDetail> opMatkulSiswa2 =
                        matkulSiswaDetailDao.findByMahasiswaNimAndMataKuliahId(matkulSiswaDetail.getMahasiswa().getNim(), matkulSiswaDto.getMatakuliah());
                if (opMatkulSiswa2.isPresent()) {
                    System.out.println("<<< DATA : " + matkulSiswaDetail.getMataKuliah().getId());
                    response.setMessage("Duplicate Data");
                    response.setData(new ArrayList<>());
                    return response;
                }

                Optional<MataKuliah> mataKuliah = mataKuliahDao.findById(matkulSiswaDto.getMatakuliah());
                matkulSiswaDetail.setMataKuliah(mataKuliah.get());
                matkulSiswaDetailDao.save(matkulSiswaDetail);
            }
            response.setMessage("Data berhasil di update");
            response.setData(new ArrayList<>());
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Data gagal di update");
            response.setData(new ArrayList<>());
            return response;
        }
    }

    @DeleteMapping("/delete")
    private Response delete(@RequestParam String id) {
        Response response = new Response();
        try {
            mahasiswaDao.deleteById(id);
            response.setMessage("Data berhasil di hapus");
            response.setData(new ArrayList<>());
            return response;
        } catch (Exception e) {
            response.setMessage("Data gagal di hapus");
            response.setData(new ArrayList<>());
            return response;
        }
    }
}
