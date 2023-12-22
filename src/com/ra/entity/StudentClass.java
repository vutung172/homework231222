package com.ra.entity;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentClass implements IStudentManagement, IValid{
    private String classId;
    private String className;
    private String description;
    private int classStatus;

    public StudentClass() {
    }

    public StudentClass(String classId, String className, String description, int classStatus) {
        this.classId = classId;
        this.className = className;
        this.description = description;
        this.classStatus = classStatus;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(int classStatus) {
        this.classStatus = classStatus;
    }

    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào mã lớp");
        classId = sc.nextLine();
        while (!isValid("J.{4}",classId)){
            System.err.println("Mã lớp không hợp lệ");
            classId = sc.nextLine();
        }
        System.out.println("Nhập tên lớp:");
        className = sc.nextLine();
        while(!isValid(".{10}",className)){
            System.out.println("Tên lớp không phù hợp");
            className = sc.nextLine();
        }
        System.out.println("Nhập vào mô tả lớp; ");
        description = sc.nextLine();
        System.out.println("Nhập vào trạng thái lớp");
        classStatus = Integer.parseInt(sc.nextLine());

    }

    @Override
    public void displayData() {
        System.out.printf("%5s | %15s | %30s | %10s |\n",getClassId(),getClassName(),getDescription(),getClassStatus());
    }

    @Override
    public <T, O> boolean isValid(T t, O obj) {
        Pattern p = Pattern.compile((String) t);
        Matcher m = p.matcher((CharSequence) obj);
        return m.matches();
    }

}
