package com.insurance.www.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
@Entity
@Table
public class StudentRegistrationEntity 
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    @Column
	@NotEmpty( message = "can not be null or empty")
	@Pattern(regexp = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$" ,message = "must be 3 or more Charecters Only ")
	@Schema(
		description = "Name of the customer", example = "VasuBabu"
	)
	private String name;
    @Column
	@NotEmpty( message = "can not be a null or empty")
	@Pattern(regexp = "^(?!.*?\\.\\.)(?!.*?\\.(_|_\\.|\\._))([a-zA-Z0-9]+[a-zA-Z]*)(?:[._][a-zA-Z0-9]+)?(?:[._]?[a-zA-Z0-9]+)?@[a-zA-Z.]+(?:_[a-zA-Z0-9]+)?\\.[a-zA-Z]{2,3}$" ,message = "please provide valid email ")
	@Schema(
		description = "Email of the customer ", example = "sixAlphabets@gmail.com"
	)
	private String email;
    @Column
	@NotEmpty( message = "can not be a null or empty")
	@Pattern(regexp = "^[6-9][0-9]{9}$" ,message = "must start with 6-9 and have 10 digits  ")
	@Schema(
		description = "mobileNumber  of the customer ", example = "9876543210"
	)
	private String mobileno;

    @Column
	@NotEmpty( message = "Cource not be a null or empty")
	
    private String course;
    @Column
	@NotEmpty( message = "Batch can not be a null or empty")

    private String batch;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobileno() {
        return mobileno;
    }
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getBatch() {
        return batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }
    public StudentRegistrationEntity(long id,
            @NotEmpty(message = "fullName can not be null or empty") @Pattern(regexp = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$", message = " Name must be 3 or more Charecters ") String name,
            @NotEmpty(message = "Email can not be a null or empty") @Pattern(regexp = "^(?!.*?\\.\\.)(?!.*?\\.(_|_\\.|\\._))([a-zA-Z0-9]+[a-zA-Z]*)(?:[._][a-zA-Z0-9]+)?(?:[._]?[a-zA-Z0-9]+)?@[a-zA-Z.]+(?:_[a-zA-Z0-9]+)?\\.[a-zA-Z]{2,3}$", message = " please provide valid email ") String email,
            @NotEmpty(message = "mobile Number can not be a null or empty") @Pattern(regexp = "^[6-9][0-9]{9}$", message = " mobileNumber must start with 6-9 and have 10 digits  ") String mobileno,
            @NotEmpty(message = "Cource not be a null or empty") String course,
            @NotEmpty(message = "Batch can not be a null or empty") String batch) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileno = mobileno;
        this.course = course;
        this.batch = batch;
    }

    public StudentRegistrationEntity()
    {

    }

}
