package com.aslan2142.chatroom.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    public static FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository)
    {
        this.fileRepository = fileRepository;
    }

    public Resource serveFile(String filename)
    {
        return fileRepository.loadFile(filename);
    }

    public void storeFile(String filename, MultipartFile uploadedFile)
    {
        fileRepository.storeFile(filename, uploadedFile);
    }

}