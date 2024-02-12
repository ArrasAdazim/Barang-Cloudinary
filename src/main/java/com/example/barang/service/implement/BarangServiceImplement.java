package com.example.barang.service.implement;

import com.example.barang.model.Barang;
import com.example.barang.model.dto.BarangModel;
import com.example.barang.repository.BarangRepository;
import com.example.barang.service.BarangService;
import com.example.barang.service.CloudinaryService;
import com.example.barang.util.TemplateRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BarangServiceImplement implements BarangService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private TemplateRespone templateRespone;
//    @Override
//    public ResponseEntity<Map> uploadImage(BarangModel barangModel) {
//        try {
//            if (barangModel.getName().isEmpty()) {
//                return ResponseEntity.badRequest().build();
//            }
//            if (barangModel.getFile().isEmpty()) {
//                return ResponseEntity.badRequest().build();
//            }
//            Barang barang = new Barang();
//            barang.setName(barangModel.getName());
//            barang.setUrl(cloudinaryService.uploadFile(barangModel.getFile(), "folder_1"));
//            barang.setKeterangan(barangModel.getKeterangan());
//            if(barang.getUrl() == null) {
//                return ResponseEntity.badRequest().build();
//            }
//            barangRepository.save(barang);
////            return ResponseEntity.ok().body(Map.of("url", barang.getUrl()));
//            return new ResponseEntity<Map>(templateRespone.berhasil(barang), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public ResponseEntity<Map> uploadImage(BarangModel barangModel) {
        try {
            if (barangModel.getName().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (barangModel.getFile().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            List<String> urls = new ArrayList<>(); // List untuk menyimpan semua URL gambar

            for (MultipartFile file : barangModel.getFile()) {
                // Validasi ukuran file
                if (file.getSize() > 10 * 1024 * 1024) { // 10 MB
//                    return ResponseEntity.badRequest().body(Map.of("message", "Ukuran file terlalu besar. Maksimal 10 MB."));
                    return new ResponseEntity<Map>(templateRespone.gagal("Ukuran file terlalu besar Maksimal 10 MB"), HttpStatus.INTERNAL_SERVER_ERROR);
                }
                String imageUrl = cloudinaryService.uploadFile(file, "folder_1");
                if (imageUrl != null) {
                    urls.add(imageUrl);
                }
            }

            Barang barang = new Barang();
            barang.setName(barangModel.getName());
            barang.setUrl(urls); // Simpan semua URL gambar
            barang.setKeterangan(barangModel.getKeterangan());

            barangRepository.save(barang);

            return new ResponseEntity<Map>(templateRespone.berhasil(barang), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public List<Barang> getAllBarang() {
//        return barangRepository.findAll();
//    }

//    @Override
//    public Map getAll(Integer page, Integer size) {
//        try {
//            Pageable showData = PageRequest.of(page,size);
//            Optional<Barang> list = barangRepository.getAllData(showData.getOffset());
//            return templateRespone.berhasil(list);
//        }catch (Exception e){
//            return templateRespone.gagal(e);
//        }
//    }
}
