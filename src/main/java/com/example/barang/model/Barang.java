package com.example.barang.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "barang")
public class Barang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_image")
    private String name;

    @ElementCollection
    @Column(name = "url_image")
//    private String url;//digunakan jika ingin menggunakan single file
    private List<String> url;

    @Column(name = "keterangan")
    private String keterangan;
}
