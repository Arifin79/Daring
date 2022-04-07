package com.arifin.daringschool.Activity.UiTeacher.Model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Assignment implements Serializable {

    private String mNameAssignmentFile;
    private String mNameAssignment;
    private String mUrlPdfTeacher;
    private String mUrlPdfStudent;
    private String mAssignmentDate;
    private String mDescAssignment;
    private String mScore;
    private String mKey;

    public Assignment() {
        //empty constructor needed
    }

    public Assignment(String nameFile, String urlPdf, String scoreStudent, String urlPdfStudent, String assignmentDate, String description, String assignmentName) {

        mNameAssignmentFile = nameFile;
        mUrlPdfTeacher = urlPdf;
        mUrlPdfStudent = urlPdfStudent;
        mAssignmentDate = assignmentDate;
        mDescAssignment = description;
        mNameAssignment = assignmentName;
        mScore = scoreStudent;
    }

    public String getmNameAssignmentFile() {
        return mNameAssignmentFile;
    }

    public void setmNameAssignmentFile(String mNameAssignmentFile) {
        this.mNameAssignmentFile = mNameAssignmentFile;
    }

    public String getmNameAssignment() {
        return mNameAssignment;
    }

    public void setmNameAssignment(String mNameAssignment) {
        this.mNameAssignment = mNameAssignment;
    }

    public String getmUrlPdfTeacher() {
        return mUrlPdfTeacher;
    }

    public void setmUrlPdfTeacher(String mUrlPdfTeacher) {
        this.mUrlPdfTeacher = mUrlPdfTeacher;
    }

    public String getmUrlPdfStudent() {
        return mUrlPdfStudent;
    }

    public void setmUrlPdfStudent(String mUrlPdfStudent) {
        this.mUrlPdfStudent = mUrlPdfStudent;
    }

    public String getmAssignmentDate() {
        return mAssignmentDate;
    }

    public void setmAssignmentDate(String mAssignmentDate) {
        this.mAssignmentDate = mAssignmentDate;
    }

    public String getmDescAssignment() {
        return mDescAssignment;
    }

    public void setmDescAssignment(String mDescAssignment) {
        this.mDescAssignment = mDescAssignment;
    }

    public String getmScore() {
        return mScore;
    }

    public void setmScore(String mScore) {
        this.mScore = mScore;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}
