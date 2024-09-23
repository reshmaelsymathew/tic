package edu.uow.tic.visitor.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String visitorName;
    private String email;
    private String phoneNumber;
    private String nationality;
    private String preferredLanguage;
    private String inquiry;
    
    @Temporal(TemporalType.DATE)
    private Date visitDate;

    public Visitor() {
    }

    public Visitor(String visitorName, String email, String phoneNumber, String nationality, String preferredLanguage, String inquiry, Date visitDate) {
        this.visitorName = visitorName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.preferredLanguage = preferredLanguage;
        this.inquiry = inquiry;
        this.visitDate = visitDate;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getInquiry() {
        return inquiry;
    }

    public void setInquiry(String inquiry) {
        this.inquiry = inquiry;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
}