package com.example.barang.controller;

import com.example.barang.model.Barang;
import com.example.barang.model.dto.BarangModel;
import com.example.barang.repository.BarangRepository;
import com.example.barang.service.BarangService;
import com.example.barang.util.TemplateRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/barang")
public class BarangController {

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private BarangService barangService;

    @Autowired
    private TemplateRespone templateRespone;

//    @PostMapping(value = "/upload")
//    public ResponseEntity<Map> upload(BarangModel barangModel) throws Exception{
//        try {
//            return barangService.uploadImage(barangModel);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
@PostMapping(value = "/upload")
public ResponseEntity<Map> upload(
        @RequestParam("name") String name,
        @RequestParam("file") List<MultipartFile> file, // Menggunakan List<MultipartFile> untuk multiple files
        @RequestParam("keterangan") String keterangan
) {
    try {
        BarangModel barangModel = new BarangModel();
        barangModel.setName(name);
        barangModel.setFile(file);
        barangModel.setKeterangan(keterangan);

        return barangService.uploadImage(barangModel);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    @GetMapping("/list")
    public  ResponseEntity<Map>list(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Barang> list = null;
         list = barangRepository.getAllData(show_data);
        return new ResponseEntity(list, new HttpHeaders(), HttpStatus.OK);
    }
}
