package mivs;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Course implements Serializable {

    private String code;
    private String tittle;
    private String desciption;
    private String startDate;
    private int credit;
    private String lecturerCode;

    public Course() {
    }

    public Course(String code, String tittle, String desciption, String startDate, int credit, String lecturerCode) {
        this.code = code;
        this.tittle = tittle;
        this.desciption = desciption;
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

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
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

        // irasymas i faila
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("files/courses"));
            outputStream.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void courseList() {
        //nuskaitymas is failo
        ObjectInputStream inputStream = null;
        HashMap<String, Course> readCourse = new HashMap<String, Course>();
        try {
            inputStream = new ObjectInputStream(new FileInputStream("files/courses"));
            readCourse = (HashMap<String, Course>) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("%-5s %-10s %-13s %-10s %-10s\n", "Code.", "Title", "StartDate", "Credit", "LecturerCode");
        for (Map.Entry<String, Course> entry : readCourse.entrySet()) {

            Course value = entry.getValue();
            System.out.printf("%-5s %-10s %-13s %-10s %-10s\n", value.getCode(), value.getTittle(), value.getStartDate(), value.getCredit(), value.getLecturerCode());
        }
    }


}
