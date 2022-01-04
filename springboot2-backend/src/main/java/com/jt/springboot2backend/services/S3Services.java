package com.jt.springboot2backend.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3Services {

    private Logger LOG = LoggerFactory.getLogger(S3Services.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String buket;

    public URI uploadFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        InputStream is = multipartFile.getInputStream();
        String contentType = multipartFile.getContentType();
        return uploadFile(is, fileName, contentType);
    }

    public URI uploadFile(InputStream is, String fileName, String contentType) {
        try {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType(contentType);
        LOG.info("Enviando o arquivo!");
        s3Client.putObject(buket, fileName, is, meta);
        return s3Client.getUrl(buket, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Erro ao converter url para uri!");
        }
    }
}
