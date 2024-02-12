package com.example.barang.model.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BarangModel {

    private String name;
//    private MultipartFile file;
    private List<MultipartFile> file;
    private String keterangan;
}
