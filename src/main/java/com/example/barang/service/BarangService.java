package com.example.barang.service;

import com.example.barang.model.Barang;
import com.example.barang.model.dto.BarangModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface BarangService {

    public ResponseEntity<Map> uploadImage(BarangModel barangModel);

//    public Map getAll ( Integer page, Integer size);

//    List<Barang> getAllBarang();
}
