package com.insurance.www.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance.www.model.ImageStatus;
import com.insurance.www.model.PropertyClaimEntity;
import com.insurance.www.model.PropertyClaimsImage;
import com.insurance.www.exception.ResourceNotFoundException;
import com.insurance.www.service.PropertyClaimsService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/propertyclaimentitys")
@CrossOrigin("*")
public class PropertyClaimsController {

    private final PropertyClaimsService propertyClaimsService;

    public PropertyClaimsController(PropertyClaimsService propertyClaimsService) {
        this.propertyClaimsService = propertyClaimsService;
    }
 
    // @PostMapping(consumes = { "multipart/form-data" })
    // public PropertyClaimEntity createPropertyClaimEntity(@RequestPart("propertyclaimentity") String propertyclaimentityString,
    //                              @RequestPart("files") List<MultipartFile> files) throws IOException {

    //     // Deserialize the propertyclaimentity JSON string into a PropertyClaimEntity object
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     PropertyClaimEntity propertyclaimentity = objectMapper.readValue( propertyclaimentityString, PropertyClaimEntity.class);
    //     return propertyClaimsService.savePropertyClaimEntity(propertyclaimentity, files);
    // }

    @PostMapping(path = "/uploadinservice", consumes = { "multipart/form-data" })
    public PropertyClaimEntity createPropertyClaimEntityInService(@RequestPart("propertyclaimentity") String propertyclaimentityString,
                                 @RequestPart("files") List<MultipartFile> files) throws IOException {

        // Deserialize the propertyclaimentity JSON string into a PropertyClaimEntity object
        ObjectMapper objectMapper = new ObjectMapper();
        PropertyClaimEntity propertyclaimentity = objectMapper.readValue( propertyclaimentityString, PropertyClaimEntity.class);
        return propertyClaimsService.savePropertyClaimEntityInService(propertyclaimentity, files);
    }

   

    @GetMapping
    public List<PropertyClaimEntity> getAllPropertyClaimEntitys() {
        return propertyClaimsService.getAllPropertyClaimEntitys();
    }

    // @GetMapping("/images/{id}")
    // public byte[] getImage(@PathVariable Long id) {
    //     return propertyClaimsService.getImageData(id);

    // }

    @GetMapping("/{id}")
    public Optional<PropertyClaimEntity> getPropertyClaimEntityById(@PathVariable Long id) {
        return propertyClaimsService.getPropertyClaimEntityById(id);
    }

    @GetMapping("/fetchAllPropertyClaimEntityWithValidImageUrls")
    public List<PropertyClaimEntity> getAllPropertyClaimEntitysWithImagesApprovedOrAppliedStatus() {
        return propertyClaimsService.getAllPropertyClaimEntitysWithApprovedOrAppliedImages();
    }

    @GetMapping("/fetchPropertyClaimEntityWithValidImageUrls/{id}")
    public ResponseEntity<PropertyClaimEntity> getPropertyClaimEntityByIdWithImagesApprovedOrAppliedStatus(@PathVariable Long id) {
        return propertyClaimsService.getPropertyClaimEntityByIdWithApprovedOrAppliedImages(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/images/{imageId}/status")
    public ResponseEntity<String> updateImageStatus(
            @PathVariable Long imageId,
            @RequestParam ImageStatus status) {
        try {
            propertyClaimsService.updateImageStatus(imageId, status);
            return ResponseEntity.ok("Image status updated successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/reupload-count")
    public ResponseEntity<Integer> getReuploadableImageCount(@PathVariable Long id) {
        int reuploadCount = propertyClaimsService.getReuploadableImageCount(id);
        return ResponseEntity.ok(reuploadCount);
    }

    // Endpoint to handle image Reuploading in service
    @PostMapping("/{id}/upload-images")
    public ResponseEntity<String> uploadImages(@PathVariable Long id, @RequestParam List<MultipartFile> images) throws IOException {
        int allowedReuploadCount = propertyClaimsService.getReuploadableImageCount(id);

        if (images.size() > allowedReuploadCount) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can only upload " + allowedReuploadCount + " images.");
        }

        // Logic to save images and update their status
        String saveReUploadImageInService = propertyClaimsService.saveReUploadImageInService(id, images);

        return ResponseEntity.ok("Images uploaded successfully."+saveReUploadImageInService);
    }

//
   


//    @GetMapping("/fetching/{filename}")
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

    @GetMapping("/fetching")
    public ResponseEntity<Resource> serveFile(@RequestParam(name = "filename") String filename) {
        try {
//            Path filePath = uploadDir.resolve(propertyclaimentityDir).resolve(filename);
            Path filePath = Paths.get(filename);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }


   

    
}
