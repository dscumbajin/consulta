package com.dscumbajin.demo.srv;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ProcesoSrv {

    public ResponseEntity<?> dowload(String data) throws IOException {
        String json = "{\"imagen\":\"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\"}\n";
        // Clase para convertir el json a un map y poder obtener el valor de imagen.
        ObjectMapper mapper = new ObjectMapper();
        // convertimos el json, en caso sea a trabes de un framework como
        // Spring Boot el objeto de respuesta tendrá la propiedad de tipo String
        Map<String, String> jsonMap = mapper.readValue(json, Map.class);
        // obtenemos el valor del campo imagen
        String imagenBase64 = jsonMap.get("imagen");
        System.out.println("El String Base64 lo decodificamos a un array de bytes");
        // El String Base64 lo decodificamos a un array de bytes
        byte[] archivoByte = Base64.getDecoder().decode(imagenBase64);
        String name = "test";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        ZipEntry entry = new ZipEntry("imagen.png");
        entry.setSize(archivoByte.length);
        zos.putNextEntry(entry);
        zos.write(archivoByte);
        zos.closeEntry();
        zos.close();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/zip"));
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + "Export-" + name + ".zip" + "\"");
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

}
