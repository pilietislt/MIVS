package mivs;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import mivs.services.*;

public class Course implements Serializable {

    private String code;
    private String tittle;
    private String description;
    private String startDate;
    private int credit;
    private String lecturerCode;

    public Course() {
    }

    public Course(String code, String tittle, String description, String startDate, int credit, String lecturerCode) {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
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

    public void addCourse() {
        Course curs = new Course("s0", "ss", "di", "2018 01 01", 5, "153");
        HashMap<String, Course> courses = new HashMap<String, Course>();
        courses.put("1", curs);

        IOService.writeObjectToFile(courses,"files/courses");
    }

    public void courseList() {

        try {
            HashMap<String, Course> readCourse = (HashMap<String, Course>) IOService.readObjectFromFile("files/courses");

            System.out.printf("%-5s %-10s %-13s %-10s %-10s\n", "Code.", "Title", "StartDate", "Credit", "LecturerCode");
            for (Map.Entry<String, Course> entry : readCourse.entrySet()) {
                Course value = entry.getValue();
                System.out.printf("%-5s %-10s %-13s %-10s %-10s\n", value.getCode(), value.getTittle(), value.getStartDate(), value.getCredit(), value.getLecturerCode());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


}
