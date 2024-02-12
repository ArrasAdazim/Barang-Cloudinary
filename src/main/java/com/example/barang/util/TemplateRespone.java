package com.example.barang.util;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component // IOC - Beans
public class TemplateRespone {

    public Map berhasil(Object objek){
        Map map = new HashMap();
        map.put("data", objek);
        map.put("message", "sukses");
        map.put("status", "200");
        return map;
    }

    public Map gagal(Object objek){
        Map map = new HashMap();
        map.put("message", objek);
        map.put("status", "400");
        return map;
    }
}
