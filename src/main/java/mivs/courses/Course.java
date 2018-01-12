package mivs.courses;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import mivs.utils.*;

public class Course implements Serializable {

    private String code;
    private String tittle;
    private String description;
    private LocalDate startDate;
    private int credit;
    private String lecturerCode;

    public Course() {
    }

    public Course(String code, String tittle, String description, LocalDate startDate, int credit, String lecturerCode) {
        this.code = code;
        this.tittle = tittle;
        this.description = description;
        this.startDate = startDate;
        this.credit = credit;
        this.lecturerCode = lecturerCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getLecturerCode() {
        return lecturerCode;
    }

    public void setLecturerCode(String lecturerCode) {
        this.lecturerCode = lecturerCode;
    }





}
