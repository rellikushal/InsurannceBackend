package com.insurance.www.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.insurance.www.model.PropertyClaimEntity;
import com.insurance.www.model.PropertyClaimsImage;
import com.insurance.www.model.ImageStatus;
import com.insurance.www.exception.ResourceNotFoundException;

import com.insurance.www.repository.PropertyClaimEntityRepository;
import com.insurance.www.repository.PropertyClaimsImageRepository;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyClaimsService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private static final String IMAGE_API_URL_TEMPLATE = "http://122.169.207.194:9092/api/products/fetching?filename=%s";


    private final PropertyClaimEntityRepository propertyClaimEntityRepository;
    private final PropertyClaimsImageRepository propertyClaimsImageRepository;

    public PropertyClaimsService(PropertyClaimEntityRepository propertyClaimEntityRepository, PropertyClaimsImageRepository propertyClaimsImageRepository) {
        this.propertyClaimEntityRepository = propertyClaimEntityRepository;
        this.propertyClaimsImageRepository = propertyClaimsImageRepository;
    }

   


    public PropertyClaimEntity savePropertyClaimEntityInService(PropertyClaimEntity propertyclaimentity, List<MultipartFile> files) throws IOException {
        propertyClaimEntityRepository.save(propertyclaimentity);

        List<PropertyClaimsImage> images = files.stream()
                .map(file -> {
                    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    Path filePath = Paths.get(uploadDir, fileName);
                    try {
                        Files.write(filePath, file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    PropertyClaimsImage image = new PropertyClaimsImage();
//                    image.setUrl("/uploads/" + fileName);
                    String relativeImagePath = uploadDir+ File.separator + fileName;
                    image.setUrl(uploadDir+ File.separator + fileName);
                    image.setApiPath(String.format(IMAGE_API_URL_TEMPLATE,relativeImagePath));
                    image.setImageStatus(ImageStatus.APPLIED);

                    image.setPropertyClaimEntity(propertyclaimentity);
                    return image;
                })
                .collect(Collectors.toList());

        propertyClaimsImageRepository.saveAll(images);
        propertyclaimentity.setImages(images);

        return propertyclaimentity;
    }



    public List<PropertyClaimEntity> getAllPropertyClaimEntitysWithApprovedOrAppliedImages() {
        return propertyClaimEntityRepository.findAllWithApprovedOrAppliedImages();
    }

    // public Optional<PropertyClaimEntity> getPropertyClaimEntityByIdWithApprovedOrAppliedImages(Long id) {


    //     Optional<PropertyClaimEntity> Data= propertyClaimEntityRepository.findByIdWithApprovedOrAppliedImages(id);
    //     Data.get().getImages().forEach(a-> a.getUrl().replace("\\", "//"));    

    //     // return propertyClaimEntityRepository.findByIdWithApprovedOrAppliedImages(id);
    //     return Data;
    // }

    public Optional<PropertyClaimEntity> getPropertyClaimEntityByIdWithApprovedOrAppliedImages(Long id) {
        Optional<PropertyClaimEntity> data = propertyClaimEntityRepository.findByIdWithApprovedOrAppliedImages(id);
    
       // Safely check if the Optional contains a value
        // data.ifPresent(entity -> {
        //     entity.getImages().forEach(image -> {
        //         image.setUrl(image.getUrl().replace("\\", "//"));
        //         image.setApiPath(image.getApiPath().replace("\\", "//"));
        //     });
        // });
    
        return data;
    }
    

    public int getReuploadableImageCount(Long claimid) {
        Optional<PropertyClaimEntity> claimopt = getPropertyClaimEntityByIdWithApprovedOrAppliedImages(claimid);
        if (claimopt.isPresent()) {
            PropertyClaimEntity propertyclaimentity = claimopt.get();
            long approvedOrAppliedCount = propertyclaimentity.getImages().stream()
//                    .filter(img -> "APPROVED".equals(img.getImageStatus()) || "APPLIED".equals(img.getImageStatus()))
                    .count();
            return 5 - (int) approvedOrAppliedCount;
        }
        return 5; // If propertyclaimentity not found or no images, allow up to 5 new uploads
    }


//    saving reuploading images in service
    public String saveReUploadImageInService(Long claimid, List<MultipartFile> files) throws IOException {
//        propertyClaimEntityRepository.save(propertyclaimentity);
        Optional<PropertyClaimEntity> claimopt = getPropertyClaimEntityByIdWithApprovedOrAppliedImages(claimid);


        if (claimopt.isPresent()) {
            PropertyClaimEntity propertyclaimentity = claimopt.get();

            List<PropertyClaimsImage> images = files.stream()
                    .map(file -> {
                        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                        Path filePath = Paths.get(uploadDir, fileName);
                        try {
                            Files.write(filePath, file.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        PropertyClaimsImage image = new PropertyClaimsImage();
//                    image.setUrl("/uploads/" + fileName);
                        image.setUrl(uploadDir + File.separator + fileName);
                        String relativeImagePath = uploadDir+ File.separator + fileName;
                        image.setApiPath(String.format(IMAGE_API_URL_TEMPLATE,relativeImagePath));
                        image.setImageStatus(ImageStatus.APPLIED);

                        image.setPropertyClaimEntity(propertyclaimentity);
                        return image;
                    })
                    .collect(Collectors.toList());

            propertyClaimsImageRepository.saveAll(images);
            propertyclaimentity.setImages(images);
            propertyClaimEntityRepository.save(propertyclaimentity);
        }
        return String.format("uploading no of images: %S  is done",files.size());

    }


    public void updateImageStatus(Long imageId, ImageStatus status) {
        PropertyClaimsImage image = propertyClaimsImageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id " + imageId+ " "+"imageId"+ imageId.toString()));
        image.setImageStatus(status);
        propertyClaimsImageRepository.save(image);
    }




  
    public List<PropertyClaimEntity> getAllPropertyClaimEntitys() {
        return propertyClaimEntityRepository.findAll();
    }

    public Optional<PropertyClaimEntity> getPropertyClaimEntityById(Long id) {
        return propertyClaimEntityRepository.findById(id);
    }


    // public PropertyClaimEntityWithImagesDTO getPropertyClaimEntityWithImages(Long id) throws IOException {
    //     Optional<PropertyClaimEntity> claimoptional = propertyClaimEntityRepository.findById(id);
    //     if (claimoptional.isPresent()) {
    //         PropertyClaimEntity propertyclaimentity = claimoptional.get();
    //         List<ImageDTO> imageDTOs = propertyclaimentity.getImages().stream()
    //                 .map(image -> {
    //                     Path imagePath = Paths.get( image.getUrl()); // Remove "/uploads/"
    //                     try {
    //                         byte[] imageData = Files.readAllBytes(imagePath);
    //                         String base64Image = Base64.getEncoder().encodeToString(imageData);
    //                         return new ImageDTO(image.getId(), base64Image,null);
    //                     } catch (IOException e) {
    //                         e.printStackTrace();
    //                         return null;
    //                     }
    //                 })
    //                 .collect(Collectors.toList());
    //         return new PropertyClaimEntityWithImagesDTO(propertyclaimentity.getId(), propertyclaimentity.getName(), imageDTOs);
    //     }
    //     return null;
    // }


    // public Optional<PropertyClaimEntityWithImagesDTO> getPropertyClaimEntityWithImagesByte(Long id) {
    //     Optional<PropertyClaimEntity> claimoptional = propertyClaimEntityRepository.findById(id);
    //     if (claimoptional.isPresent()) {
    //         PropertyClaimEntity propertyclaimentity = claimoptional.get();
    //         List<ImageDTO> imageDTOs = propertyclaimentity.getImages().stream()
    //                 .map(image -> {
    //                     Path filePath = Paths.get(image.getUrl());
    //                     byte[] imageData = null;
    //                     try {
    //                         imageData = Files.readAllBytes(filePath);
    //                     } catch (IOException e) {
    //                         e.printStackTrace();
    //                     }
    //                     return new ImageDTO(image.getId(), filePath.getFileName().toString(), imageData);
    //                 })
    //                 .collect(Collectors.toList());

    //         return Optional.of(new PropertyClaimEntityWithImagesDTO(propertyclaimentity.getId(), propertyclaimentity.getName(), imageDTOs));
    //     }
    //     return Optional.empty();
    // }

    // public byte[] getImageData1(Long imageId) {
    //     return propertyClaimsImageRepository.findById(imageId).map(PropertyClaimsImage::getData).orElse(null);
    // }

    // public byte[] getImageData(Long id) {
    //     Optional<PropertyClaimEntity> claimoptional = propertyClaimEntityRepository.findById(id);
    //     if (claimoptional.isPresent()) {
    //         return claimoptional.get().getImageData();
    //     }
    //     return new byte[0];
    // }
    
}
