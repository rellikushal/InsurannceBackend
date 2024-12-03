package com.insurance.www.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.insurance.www.exception.ResourceNotFoundException;
// import com.insurance.www.model.StructureAndDetails;
import com.insurance.www.model.StudentRegistrationEntity;
import com.insurance.www.repository.StudentRegistrationRepository;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("studentRegistration/api/")
public class StudentRegistrationController 
{
    @Autowired
    StudentRegistrationRepository studentRegistrationRepository;

    @GetMapping("/getallRegisteredDetails")
    public List<StudentRegistrationEntity> getAllStudentRegitseredDetails() 
    {
       return  studentRegistrationRepository.findAll();
    }

    @GetMapping("/getStudentDetailsById/{id}")
    public Optional<StudentRegistrationEntity> getMethodName(@PathVariable Long id) {
        return studentRegistrationRepository.findById(id);
    }
    

    @PostMapping("/createStudentRegistrationDetails")
    public ResponseEntity<String> registerStudent(@Valid @RequestBody StudentRegistrationEntity request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // If validation fails, return bad request with custom error message
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            bindingResult.getFieldErrors().forEach(error -> {
                errorMessage.append(error.getField()).append(" ").append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        studentRegistrationRepository.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student registered successfully");
        
        //  return ResponseEntity.status(HttpStatus.OK).body("Student registered successfully.");
    }

    @PutMapping("/updateStudentDetailsById/{id}")
public ResponseEntity<Object> upDateStructureDetailsById(@PathVariable Long id,
@Valid   @RequestBody StudentRegistrationEntity entity, BindingResult bindingResult) {
    Optional<StudentRegistrationEntity> existingDetailsOptional = studentRegistrationRepository.findById(id);
    if (existingDetailsOptional.isPresent()) {
        StudentRegistrationEntity existingDetails = existingDetailsOptional.get();

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // If validation fails, return bad request with custom error message
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }

        existingDetails.setName(entity.getName());
        existingDetails.setEmail(entity.getEmail());
        existingDetails.setMobileno(entity.getMobileno());
        existingDetails.setCourse(entity.getCourse());
        existingDetails.setBatch(entity.getBatch());

        studentRegistrationRepository.save(existingDetails);

        // return ResponseEntity.ok("Student details updated successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body("Student details updated successfully");
    } else {
        // If the resource does not exist, return HTTP status 404 (Not Found)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
    }
}

    @DeleteMapping("/deleteStudentDetailsById/{id}")
    public ResponseEntity<String> deleteStudentRegistrationdetailsbyId(@PathVariable Long id) {
    Optional<StudentRegistrationEntity> existingDetailsOptional = studentRegistrationRepository.findById(id);
    
     if (existingDetailsOptional.isPresent()) {
        studentRegistrationRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Resource deleted successfully");
    } else {
        // If the resource does not exist, return HTTP status 404 (Not Found)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
    }
    }
    
    
}
