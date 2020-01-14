package com.training.majorkafkaproject.entity;

import java.util.Date;

public class Employee {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Long experience;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "{" +
                "\"firstName\":"+" \""+ firstName + "\"" +
                ",\n"+ "\"lastName\":"+" \""+lastName + "\""+
                ",\n"+ "\"dateOfBirth\":" +" \""+dateOfBirth +"\""+
                ",\n"+ "\"experience\": " + experience +
                "\n},";
    }
}