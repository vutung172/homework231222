package com.ra.run;

import com.ra.entity.IStudentManagement;
import com.ra.entity.Student;
import com.ra.entity.StudentClass;

import java.util.*;

public class StudentManagement{
    static List<Student> students = AcademyManagement.getStudents();
    private static String select;
    private static int choice;


    public static void displayData(Scanner sc) {
        do {
            System.out.println("***********************QUẢN LÝ SINH VIÊN******************");
            System.out.println("1. Thêm mới sinh viên");
            System.out.println("2. Cập nhật thông tin sinh viên");
            System.out.println("3. Hiển thị thông tin sinh viên");
            System.out.println("4. Tính điểm trung bình");
            System.out.println("5. Xếp loại sinh viên");
            System.out.println("6. Sắp xếp sinh viên theo điểm trung bình tăng dần");
            System.out.println("7. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("8. Thống kê số sinh viên đạt loại giỏi, khá, trung bình và yếu");
            System.out.println("9. Thống kê các sinh viên Pass qua các môn học");
            System.out.println("10.Thoát");
            System.out.println("Mời bạn nhập lựa chọn (1-10)");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    updateStudent(sc);
                    break;
                case 3:
                    show();
                    break;
                case 4:
                    students.forEach(Student::calAvgMark);
                    break;
                case 5:
                    students.forEach(Student::getGPA);
                    break;
                case 6:
                    students.sort((st1, st2) -> (int) (st1.getAvgMark() - st2.getAvgMark()));
                    break;
                case 7:
                    do {
                        System.out.println("Nhập vào tên sinh viên muốn tìm");
                        String str = sc.nextLine();
                        System.out.printf("%10s | %20s | %10s | %10s | %10s | %10s | %10s | %10s | %10s | %10s | %15s |\n","ID","Họ và tên","Tuổi","Giới tính","Lớp","Điểm JavaScript","Điểm JavaCore","Điểm JavaWeb","Điểm TB","Loại","Tình trạng");
                        students.forEach(st -> {
                            if (st.getStudentName().contains(str)){
                                st.displayData();
                            }
                        } );
                        System.out.println("Bạn có muốn tiếp tục tìm tên học sinh (Y/N):");
                        select = sc.nextLine();
                    } while(select.equalsIgnoreCase("Y"));
                    break;
                case 8:
                    sumGpa();
                    break;
                case 9:
                    sumPass();
                    break;
                case 10:
                    AcademyManagement.display();
                    break;
                default:
                    System.out.println("Lựa chọn của bạn không phù hợp");
            }

        } while (true);
    }

    public static void addStudent(Scanner sc) {
        do {
            Student student = new Student();
            student.inputData(sc);
            students.add(student);
            System.out.println("Thêm thành công");
            System.out.println("Bạn có muốn tiếp tục thêm sinh viên (Y/N):");
            select = sc.nextLine();
        } while (select.equalsIgnoreCase("Y"));
    }
    public static void show(){
        System.out.printf("%10s | %50s | %10s | %10s | %10s | %10s | %10s | %10s | %10s | %10s | %15s |\n","ID","Họ và tên","Tuổi","Giới tính","Lớp","Điểm JavaScript","Điểm JavaCore","Điểm JavaWeb","Điểm TB","Loại","Tình trạng");
        students.forEach(Student::displayData);
    }

    public static void updateStudent(Scanner sc) {
        do {
            System.out.println("Nhập vào ID sinh viên bạn muốn cập nhật");
            String id = sc.nextLine();
            int count = 0;
            for (Student st : students) {
                if (st.searchByID(id) != null) {
                    count++;
                    do {
                        String string;
                        System.out.printf("%10s | %50s | %10s | %10s | %10s | %10s | %10s | %10s | %10s | %10s | %15s |\n","ID","Họ và tên","Tuổi","Giới tính","Lớp","Điểm JavaScript","Điểm JavaCore","Điểm JavaWeb","Điểm TB","Loại","Tình trạng");
                        st.displayData();
                        System.out.println("Chọn mục bạn muốn cập nhật:");
                        System.out.println("1. Tên sinh viên");
                        System.out.println("2. Tuổi");
                        System.out.println("3. Giới tính");
                        System.out.println("4. Lớp học");
                        System.out.println("5. Thêm điểm");
                        System.out.println("6. Sửa điểm");
                        System.out.println("7. Thoát");
                        System.out.println("Lựa chọn của bạn (1-7):");
                        int choice = Integer.parseInt(sc.nextLine());
                        switch (choice) {
                            case 1:
                                System.out.println("Nhập tên học sinh:");
                                string = sc.nextLine();
                                st.setStudentName(string);
                                break;
                            case 2:
                                System.out.println("Nhập tuổi:");
                                int age = Integer.parseInt(sc.nextLine());
                                st.setAge(age);
                                break;
                            case 3:
                                System.out.println("Nhập giới tính: ");
                                boolean sex = Boolean.parseBoolean(sc.nextLine());
                                st.setSex(sex);
                                break;
                            case 4:
                                AcademyManagement.getClasses().forEach(c -> {
                                    System.out.printf("%5s | %15s | %30s | %10s |\n", "ID", "Tên lớp", "Mô tả", "Trạng thái");
                                    c.displayData();
                                });
                                do {
                                    System.out.println("Nhập vào mã lớp bạn muốn chuyển vào:");
                                    select = sc.nextLine();
                                    for (StudentClass c : AcademyManagement.getClasses()) {
                                        if (c.searchByID(select) != null) {
                                            st.setStudentClass(c);
                                            count++;
                                            break;
                                        }
                                    }
                                    if (count == 0) {
                                        System.err.println("Lớp bạn chọn không tồn tại, mời nhập lại");
                                    }
                                } while (count == 0);
                                break;
                            case 5:
                                System.out.println("Bạn muốn thêm điểm môn nào?");
                                System.out.println("1. Java Script");
                                System.out.println("2. Java Core");
                                System.out.println("3. Java Web");
                                System.out.println("4. Thoát");
                                System.out.println("Lựa chọn của bạn (1-4)");
                                choice = Integer.parseInt(sc.nextLine());
                                addPoint(st, choice, sc);
                                break;
                            case 6:
                                System.out.println("Bạn muốn xóa điểm môn nào?");
                                System.out.println("1. Java Script");
                                System.out.println("2. Java Core");
                                System.out.println("3. Java Web");
                                System.out.println("4. Thoát");
                                System.out.println("Lựa chọn của bạn (1-4)");
                                choice = Integer.parseInt(sc.nextLine());
                                updatePoint(st, choice, sc);
                                break;
                            case 7:
                                break;
                            default:
                                System.out.println("Lựa chọn không phù hợp");
                        }
                        System.out.println("Cập nhật thành công");
                        System.out.println("Bạn có muốn tiếp tục cập nhật thông tin (Y/N):");
                        select = sc.nextLine();
                    } while (select.equalsIgnoreCase("Y"));
                }
            }
            select = "N";
            if (count == 0) {
                System.err.println("ID lớp không tồn tại");
                System.out.println("Bạn có muốn tiếp tục (Y/N):");
                select = sc.nextLine();
            }
        } while (select.equalsIgnoreCase("Y"));

    }

    public static void addPoint(Student st, int choice, Scanner sc) {
        switch (choice) {
            case 1:
                do {
                    System.out.println("Nhập vào điểm môn Java Script: ");
                    float pointJS = Float.parseFloat(sc.nextLine());
                    st.getListMarkJavaScript().addLast(pointJS);
                    System.out.println("Bạn có muốn tiếp tục thêm điểm môn Java Script (Y/N):");
                    select = sc.nextLine();
                } while (select.equalsIgnoreCase("Y"));
                break;
            case 2:
                do {
                    System.out.println("Nhập vào điểm môn Java Core: ");
                    float pointJC = Float.parseFloat(sc.nextLine());
                    st.getListMarkJavaCore().addLast(pointJC);
                    System.out.println("Bạn có muốn tiếp tục thêm điểm môn Java Core (Y/N):");
                    select = sc.nextLine();
                } while (select.equalsIgnoreCase("Y"));
                break;
            case 3:
                do {
                    System.out.println("Nhập vào điểm môn Java Web: ");
                    float pointJW = Float.parseFloat(sc.nextLine());
                    st.getListMarkJavaWeb().addLast(pointJW);
                    System.out.println("Bạn có muốn tiếp tục thêm điểm môn Java Web (Y/N):");
                    select = sc.nextLine();
                } while (select.equalsIgnoreCase("Y"));
                break;
            case 4:
                displayData(sc);
                break;
            default:
                System.out.println("Lựa chọn không phù hợp");
        }
    }

    public static void updatePoint(Student st, int choice, Scanner sc) {
        switch (choice) {
            case 1:
                do {
                    System.out.println("Điểm Java Script");
                    for (int i = 0; i < st.getListMarkJavaScript().size(); i++) {
                        System.out.printf(" %s |\n %s |", "lần " + (i + 1), st.getListMarkJavaScript().get(i));
                    }
                    System.out.println("Nhập lần muốn sửa: ");
                    int index = Integer.parseInt(sc.nextLine());
                    if (index <= 0 || st.getListMarkJavaScript().size() < index) {
                        System.out.println("Lần muốn sửa không tồn tại");
                    } else {
                        System.out.println("Nhập điểm số mới");
                        float pointJS = Float.parseFloat(sc.nextLine());
                        st.getListMarkJavaScript().add(index - 1, pointJS);
                    }
                    System.out.println("Bạn có muốn tiếp tục (Y/N):");
                    select = sc.nextLine();
                } while (select.equalsIgnoreCase("Y"));
                break;
            case 2:
                do {
                    System.out.println("Điểm Java Core");
                    for (int i = 0; i < st.getListMarkJavaCore().size(); i++) {
                        System.out.printf(" %s |\n %s |", "lần " + (i + 1), st.getListMarkJavaCore().get(i));
                    }
                    System.out.println("Nhập lần muốn sửa: ");
                    int index = Integer.parseInt(sc.nextLine());
                    if (index <= 0 || st.getListMarkJavaCore().size() < index) {
                        System.out.println("Lần muốn sửa không tồn tại");
                    } else {
                        System.out.println("Nhập điểm số mới");
                        float pointJC = Float.parseFloat(sc.nextLine());
                        st.getListMarkJavaCore().add(index - 1, pointJC);
                    }
                    System.out.println("Bạn có muốn tiếp tục (Y/N):");
                    select = sc.nextLine();
                } while (select.equalsIgnoreCase("Y"));
                break;
            case 3:
                do {
                    System.out.println("Điểm Java Web");
                    for (int i = 0; i < st.getListMarkJavaWeb().size(); i++) {
                        System.out.printf(" %s |\n %s |", "lần " + (i + 1), st.getListMarkJavaWeb().get(i));
                    }
                    System.out.println("Nhập lần muốn sửa: ");
                    int index = Integer.parseInt(sc.nextLine());
                    if (index <= 0 || st.getListMarkJavaWeb().size() < index) {
                        System.out.println("Lần muốn sửa không tồn tại");
                    } else {
                        System.out.println("Nhập điểm số mới");
                        float pointJW = Float.parseFloat(sc.nextLine());
                        st.getListMarkJavaWeb().add(index - 1, pointJW);
                    }
                    System.out.println("Bạn có muốn tiếp tục (Y/N):");
                    select = sc.nextLine();
                } while (select.equalsIgnoreCase("Y"));
                break;
            case 4:
                break;
            default:
                System.out.println("Lựa chọn không phù hợp");
        }
    }

    public static void sumGpa(){
        int countA = 0, countB =0 ,countC = 0,countF = 0,countU = 0;
        Map<String,Integer> gpas = new HashMap<>();
        System.out.printf(" %10s | %10s |\n","Xếp Loại","Số lượng");
        for (Student st:students) {
            switch (st.getGpa()){
                case "Giỏi":
                    countA++;
                    gpas.put("Giỏi",countA);
                    break;
                case "Khá":
                    countB++;
                    gpas.put("Khá",countB);
                    break;
                case "Trung bình":
                    countC++;
                    gpas.put("Trung bình",countC);
                    break;
                case "Yếu":
                    countF++;
                    gpas.put("Yếu",countF);
                    break;
                default:
                    countU++;
                    gpas.put("Chưa xếp loại",countF);
                    break;
            }
        }
        gpas.forEach((key, value) -> System.out.printf(" %10s | %10s |\n",key,value));
    }

    public static void sumPass(){
        int countPass = 0;
        for (Student st: students) {
            if (st.getAvgMark() >=5){
                countPass++;
            }
        }
        System.out.printf("Số lượng sinh viên qua môn là %d\n",countPass);
    }

}
