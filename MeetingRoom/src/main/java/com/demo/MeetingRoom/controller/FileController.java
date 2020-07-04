package com.demo.MeetingRoom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private ServletContext servletContext;

    private final static String BASE_PATH = "C:/photos/";

    @RequestMapping("/resources")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam(value = "fileName") String fileName) throws IOException {
        System.out.println("file controller download file - " + BASE_PATH);
        String mineType = this.servletContext.getMimeType(fileName);
        MediaType mediaType = MediaType.parseMediaType(mineType);

        File file = new File(BASE_PATH + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length())
                .body(resource);
    }

    public static String saveFile(MultipartFile file) throws IOException {
        String fName = "",
                extension = "",
                companyImageName = "";

        System.out.println("file controller save file - " + BASE_PATH);

        fName = file.getOriginalFilename().split("\\.")[0].replaceAll("\\p{Punct}", "").replaceAll("\\s+", "");
        extension = file.getOriginalFilename().split("\\.")[1];
        companyImageName = fName + "." + extension;
        byte[] bytes = file.getBytes();
        Path path = Paths.get(BASE_PATH + companyImageName);
        Files.write(path, bytes);
        return companyImageName;

    }

}
