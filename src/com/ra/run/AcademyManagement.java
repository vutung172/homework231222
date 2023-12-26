package com.ra.run;

import com.ra.entity.Student;
import com.ra.entity.StudentClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AcademyManagement {
    public static List<StudentClass> classes = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();

    public static List<StudentClass> getClasses() {
        return classes;
    }

    public static void setClasses(List<StudentClass> classes) {
        AcademyManagement.classes = classes;
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static void setStudents(List<Student> students) {
        AcademyManagement.students = students;
    }

    public static void display(){
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("**********************QUẢN LÝ HỌC VIỆN*******************");
            System.out.println("1. Quản lý lớp");
            System.out.println("2. Quản lý sinh viên");
            System.out.println("3. Thoát");
            System.out.print("Mời bạn nhập lựa chọn (1-3): ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    ClassManagement.displayData(sc);
                    break;
                case 2:
                    StudentManagement.displayData(sc);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn của bạn không phù hợp");
            }

        } while (true);
    }

    public static void writeListStudent(){
        try {
            FileWriter listStudent = new FileWriter(".storage.listStudent.text");
            listStudent.write(students.toString());
            listStudent.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeListClass(){
        try {
            FileWriter listClass = new FileWriter("listClass.text");
            classes.forEach(c -> {
                try {
                    listClass.write(c.getClassId()+",");
                    listClass.write(c.getClassName()+",");
                    listClass.write(c.getDescription()+",");
                    listClass.write(c.getClassStatus()+",\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            listClass.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
