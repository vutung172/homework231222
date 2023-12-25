package com.ra.entity;

import com.ra.run.AcademyManagement;
import com.ra.run.ClassManagement;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentClass implements IStudentManagement, IValid, ISearch {
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
        int count;
        do {
            count = 0;
            System.out.println("Nhập vào mã lớp");
            classId = sc.nextLine();
            if (!isValid("J.{4}", classId)) {
                System.err.println("Mã lớp không hợp lệ");
            }
            for (StudentClass c : AcademyManagement.getClasses()) {
                if (c.getClassId().equals(classId)) {
                    System.err.println("Mã lớp học đã tồn tại, mời nhập mã khác");
                    count++;
                    break;
                }
            }
        } while (!isValid("J.{4}", classId) || count != 0);
        do {
            System.out.println("Nhập tên lớp:");
            className = sc.nextLine();
            if (!isValid(".{10}", className)) {
                System.err.println("Tên lớp không phù hợp");
            }
        } while (!isValid(".{10}", className));
        System.out.println("Nhập vào mô tả lớp; ");
        description = sc.nextLine();
        System.out.println("Nhập vào trạng thái lớp");
        classStatus = Integer.parseInt(sc.nextLine());
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

    @Override
    public <T, O> boolean isValid(T t, O obj) {
        Pattern p = Pattern.compile((String) t);
        Matcher m = p.matcher((CharSequence) obj);
        return m.matches();
    }


    @Override
    public StudentClass searchByID(String id) {
        if (this.classId.equals(id))
            return this;
        return null;
    }

    @Override
    public void searchByString(String string) {

    }
}
