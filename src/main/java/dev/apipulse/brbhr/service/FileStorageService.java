package dev.apipulse.brbhr.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public String store(InputStream inputStream, String filename,String contentType) {
        // Store file in GridFS
        String fileId = gridFsTemplate.store(inputStream, filename, contentType).toString();
        return fileId; // Return the file ID for later retrieval
    }

    public GridFsResource loadFileAsResource(String fileId) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new org.springframework.data.mongodb.core.query.Query(Criteria.where("_id").is(fileId)));

        if (gridFSFile == null) {
            throw new RuntimeException("File not found with id " + fileId);
        }

        return gridFsTemplate.getResource(gridFSFile);
    }


}
