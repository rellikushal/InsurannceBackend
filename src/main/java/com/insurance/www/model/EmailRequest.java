package com.insurance.www.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// import lombok.Data;

// @Data
@Table
@Entity
public class EmailRequest 
{
    @Id
    private long id;
    private String to;
    private String toName;
    private String subject;
    private String htmlBody;	
		private String from;
	    public String getFrom() {
            return from;
        }
        public void setFrom(String from) {
            this.from = from;
        }
        public String getTo() {
            return to;
        }
        public void setTo(String to) {
            this.to = to;
        }
        public String getToName() {
            return toName;
        }
        public void setToName(String toName) {
            this.toName = toName;
        }
        public String getSubject() {
            return subject;
        }
        public void setSubject(String subject) {
            this.subject = subject;
        }
        public String getHtmlBody() {
            return htmlBody;
        }
        public void setHtmlBody(String htmlBody) {
            this.htmlBody = htmlBody;
        }
       
}