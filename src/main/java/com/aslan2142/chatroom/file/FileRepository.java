package com.aslan2142.chatroom.file;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Repository
public class FileRepository {

    public Resource loadFile(String filename)
    {
        return new FileSystemResource("/home/pi/Services/Backend/chatroom_media/" + filename);
    }

    public void storeFile(String filename, MultipartFile uploadedFile)
    {
        try {
            File file = new File("/home/pi/Services/Backend/chatroom_media/" + filename);
            uploadedFile.transferTo(file);
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

}