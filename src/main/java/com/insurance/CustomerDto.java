package com.insurance;

public class CustomerDto 
{

        private String mobileno;
        private String email;
        private String gender;
        private String fullname;
        private String dob;
        private String pancard;

        public String getMobileno() {
            return mobileno;
        }
        public void setMobileno(String mobileno) {
            this.mobileno = mobileno;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getGender() {
            return gender;
        }
        public void setGender(String gender) {
            this.gender = gender;
        }
        public String getFullname() {
            return fullname;
        }
        public void setFullname(String fullname) {
            this.fullname = fullname;
        }
        public String getDob() {
            return dob;
        }
        public void setDob(String dob) {
            this.dob = dob;
        }
        public String getPancard() {
            return pancard;
        }
        public void setPancard(String pancard) {
            this.pancard = pancard;
        }
        
        public CustomerDto(String mobileno, String email, String gender, String fullname, String dob, String pancard) {
            this.mobileno = mobileno;
            this.email = email;
            this.gender = gender;
            this.fullname = fullname;
            this.dob = dob;
            this.pancard = pancard;
        }
        public CustomerDto() {
            //TODO Auto-generated constructor stub
            super();
        }
        
}
