package com.example.ContractManager.service;

import com.example.ContractManager.model.ImageContract;
import com.example.ContractManager.repository.FileSystemRepository;
import com.example.ContractManager.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;

@Service
public class FileLocationService {
    @Autowired
    FileSystemRepository fileSystemRepository;
    @Autowired
    ImageRepository imageRepository;

    public Long save(byte[] bytes, String imageName) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);

        return imageRepository.save(new ImageContract(imageName, location))
                .getId();
    }
    public FileSystemResource find(Long imageId) {
        ImageContract image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(image.getLocation());
    }
}
