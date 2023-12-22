package com.ra.run;

import com.ra.entity.Student;
import com.ra.entity.StudentClass;

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
            System.out.println("Mời bạn nhập lựa chọn");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    ClassManagement.displayData();
                    break;
                case 2:

                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn của bạn không phù hợp");
            }

        } while (true);
    }
}
