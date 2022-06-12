create table fakultas
(
    id varchar(36)  not null,
    nama_fakultas varchar(100),
    primary key (id)
) engine=InnoDB;

create table jurusan
(
    id varchar(36)  not null,
    nama_jurusan varchar(100),
    primary key (id)
) engine=InnoDB;

create table mata_kuliah
(
    id varchar(36)  not null,
    nama_matkul varchar(100),
    primary key (id)
) engine=InnoDB;

create table mahasiswa
(
    id varchar(36)  not null,
    nim varchar(10),
    nama_siswa varchar(100),
    id_jurusan varchar(100),
    id_fakultas varchar(100),
    primary key (id)
) engine=InnoDB;

create table mata_kuliah_siswa_detail
(
    id varchar(36)  not null,
    id_mahasiswa varchar(100),
    id_matakuliah varchar(100),
    primary key (id)
) engine=InnoDB;

ALTER TABLE `mahasiswa`
    ADD CONSTRAINT `FKg9os4isbs19ssfahravousxes` FOREIGN KEY (`id_jurusan`) REFERENCES `jurusan` (`id`),
    ADD CONSTRAINT `FKnqcv2qdac1phe20qqnyi6n1n` FOREIGN KEY (`id_fakultas`) REFERENCES `fakultas` (`id`);

ALTER TABLE `mata_kuliah_siswa_detail`
    ADD CONSTRAINT `FKg9os4isbs19ssfahravouser` FOREIGN KEY (`id_mahasiswa`) REFERENCES `mahasiswa` (`id`),
    ADD CONSTRAINT `FKnqcv2qdac1phe20qqnyi6kol` FOREIGN KEY (`id_matakuliah`) REFERENCES `mata_kuliah` (`id`);

