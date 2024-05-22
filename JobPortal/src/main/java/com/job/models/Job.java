package com.job.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Job {
    private int jobId;
    private int userId;
    private String title;
    private String description;
    private String location;
    private double salary;
    private Timestamp postDate;
    private Date expiryDate;
    private String otherDetails;

    public Job() {
    }

    public Job(int jobId, int userId, String title, String description, String location, double salary,
               Timestamp postDate, Date expiryDate, String otherDetails) {
        this.jobId = jobId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.postDate = postDate;
        this.expiryDate = expiryDate;
        this.otherDetails = otherDetails;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                ", postDate=" + postDate +
                ", expiryDate=" + expiryDate +
                ", otherDetails='" + otherDetails + '\'' +
                '}';
    }
}
