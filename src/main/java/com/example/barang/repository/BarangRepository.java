package com.example.barang.repository;


import com.example.barang.model.Barang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BarangRepository extends JpaRepository <Barang, Long > {

    @Query("select c from Barang c ")
    public Page <Barang> getAllData(Pageable pageable);
}
