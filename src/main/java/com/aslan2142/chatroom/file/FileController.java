package com.aslan2142.chatroom.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/v1/file")
@RestController
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService)
    {
        this.fileService = fileService;
    }

    @GetMapping(path = "{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable("filename") String filename)
    {
        Resource file = fileService.serveFile(filename);
        
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping(path = "{filename}")
    public void uploadFile(@PathVariable("filename") String filename, @RequestParam("file") MultipartFile file)
    {
        fileService.storeFile(filename, file);
    }

}