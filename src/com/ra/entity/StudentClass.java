package com.ra.entity;

import com.ra.run.AcademyManagement;
import com.ra.run.ClassManagement;

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
    public void inputData(Scanner sc) {
        //Nhập ID cảu lớp học, kiểm tra hợp lệ, kiểm tra trùng hợp
        inputClassId(sc);
        //Nhập tên lớp, kiểm tra độ dài
        inputClassName(sc);
        //Nhập môt tả lớp học
        inputDescription(sc);
        // Nhập trạng thái lớp
        inputClassStatus(sc);
    }

    //Các method cảu input
    public void inputClassId(Scanner sc) {
        do {
            System.out.println("Nhập vào mã lớp");
            String classId = sc.nextLine();
            StudentClass cl = ClassManagement.searchClassByID(classId);
            if (!isValid("J.{4}", classId)) {
                System.err.println("Mã lớp không hợp lệ");
            } else if (cl != null) {
                System.err.println("Mã sinh viên đã tồn tại, mời nhập mã khác");
            } else {
                this.classId = classId;
                break;
            }
        } while (true);
    }

    public void inputClassName(Scanner sc) {
        do {
            System.out.println("Nhập tên lớp: ");
            this.className = sc.nextLine();
            if (!isValid(".{10}", this.className)) {
                System.err.println("Tên lớp không phù hợp");
            }
        } while (!isValid(".{10}", this.className));
    }

    public void inputDescription(Scanner sc) {
        System.out.println("Nhập vào mô tả lớp: ");
        this.description = sc.nextLine();
    }

    public void inputClassStatus(Scanner sc) {
        do {
            System.out.println("Nhập vào trạng thái lớp");
            System.out.println("0. Chờ lớp ");
            System.out.println("1. Hoạt động ");
            System.out.println("2. Tạm dừng ");
            System.out.println("3. Kết thúc ");
            System.out.print("Mời lựa chọn (0-3): ");
            this.classStatus = Integer.parseInt(sc.nextLine());
            if (0 <= this.classStatus && this.classStatus <= 3) {
                break;
            } else {
                System.err.println("Trạng thái nhập không đúng, mời nhập lại");
            }
        } while (true);
    }

    @Override
    public void displayData() {
        System.out.printf("%5s | %15s | %30s | %10s |\n", getClassId(), getClassName(), getDescription(), switch (getClassStatus()) {
            case 0:
                yield "Chờ lớp";
            case 1:
                yield "Hoạt động";
            case 2:
                yield "Tạm dừng";
            default:
                yield "Kết thúc";
        });
    }

    //Method của class Student
    @Override
    public <T, O> boolean isValid(T t, O obj) {
        Pattern p = Pattern.compile((String) t);
        Matcher m = p.matcher((CharSequence) obj);
        return m.matches();
    }
}
