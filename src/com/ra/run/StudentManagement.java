package com.ra.run;

import com.ra.entity.ISearch;
import com.ra.entity.IStudentManagement;
import com.ra.entity.Student;
import com.ra.entity.StudentClass;

import java.util.*;

public class StudentManagement {
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
            System.out.print("Mời bạn nhập lựa chọn (1-10): ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    do {
                        System.out.println("Nhập vào ID sinh viên bạn muốn cập nhật: ");
                        String id = sc.nextLine();
                        updateStudent(id, sc);
                        System.out.println("Bạn có muốn tiếp tục cập nhật sinh viên khác (Y/N):");
                        select = sc.nextLine();
                    } while (select.equalsIgnoreCase("Y"));
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
                    sortStudentByAvgMark();
                    break;
                case 7:
                    searchByStudentName(sc);
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
                    System.err.println("Lựa chọn của bạn không phù hợp");
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

    public static void show() {
        System.out.printf("%10s | %50s | %10s | %10s | %15s | %15s | %15s | %15s | %8s | %10s | %15s |\n", "ID", "Họ và tên", "Tuổi", "Giới tính", "Lớp", "Điểm JavaScript", "Điểm JavaCore", "Điểm JavaWeb", "Điểm TB", "Loại", "Tình trạng");
        students.forEach(Student::displayData);
    }

    public static void updateStudent(String id, Scanner sc) {
        Student st = searchByID(id);
        if (st != null) {
            do {
                String string;
                System.out.printf("%10s | %50s | %10s | %10s | %15s | %15s | %15s | %15s | %8s | %10s | %15s |\n", "ID", "Họ và tên", "Tuổi", "Giới tính", "Lớp", "Điểm JavaScript", "Điểm JavaCore", "Điểm JavaWeb", "Điểm TB", "Loại", "Tình trạng");
                st.displayData();
                System.out.println("Chọn mục bạn muốn cập nhật:");
                System.out.println("1. Tên sinh viên");
                System.out.println("2. Tuổi");
                System.out.println("3. Giới tính");
                System.out.println("4. Lớp học");
                System.out.println("5. Thêm điểm");
                System.out.println("6. Sửa điểm");
                System.out.println("7. Thoát");
                System.out.print("Lựa chọn của bạn (1-7): ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        st.inputStudentName(sc);
                        System.out.println("Cập nhật tên sinh viên thành công");
                        break;
                    case 2:
                        st.inputAge(sc);
                        System.out.println("Cập nhật tuổi thành công");
                        break;
                    case 3:
                        st.inputSex(sc);
                        System.out.println("Cập nhật giới tính thành công");
                        break;
                    case 4:
                        st.inputClass(sc);
                        System.out.println("Cập nhật lớp thành công");
                        break;
                    case 5:
                        st.inputMark(sc);
                        break;
                    case 6:
                        updatePoint(st, sc);
                        break;
                    case 7:
                        StudentManagement.displayData(sc);
                        break;
                    default:
                        System.out.println("Lựa chọn không phù hợp");
                }
                System.out.printf("Bạn có muốn tiếp tục cập nhật thông tin của sinh viên %s (Y/N):", st.getStudentName());
                select = sc.nextLine();
            } while (select.equalsIgnoreCase("Y"));
        } else {
            System.err.println("ID sinh viên không tồn tại");
        }
    }


    public static void updatePoint(Student st, Scanner sc) {
        do {
            System.out.println("Bạn muốn sửa điểm môn nào?");
            System.out.println("1. Java Script");
            System.out.println("2. Java Core");
            System.out.println("3. Java Web");
            System.out.println("4. Thoát");
            System.out.println("Lựa chọn của bạn (1-4)");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    if (!st.getListMarkJavaScript().isEmpty()) {
                        do {
                            System.out.println("Điểm Java Script");
                            for (int i = 0; i < st.getListMarkJavaScript().size(); i++) {
                                System.out.printf(" %s |\n %s |", "lần " + (i + 1), st.getListMarkJavaScript().get(i));
                            }
                            System.out.println("Nhập lần muốn sửa: ");
                            int index = Integer.parseInt(sc.nextLine());
                            if (index <= 0 || st.getListMarkJavaScript().size() < index) {
                                System.err.println("Lần muốn sửa không tồn tại");
                            } else {
                                System.out.println("Nhập điểm số mới: ");
                                float pointJS = Float.parseFloat(sc.nextLine());
                                st.getListMarkJavaScript().add(index - 1, pointJS);
                                System.out.println("Sửa điểm môn JavaScript thành công !");
                            }
                            System.out.println("Bạn có muốn tiếp tục sửa điểm Java Script (Y/N):");
                            select = sc.nextLine();
                        } while (select.equalsIgnoreCase("Y"));
                    } else {
                        System.err.printf("Sinh viên %s chưa có điểm môn JavaScript.", st.getStudentName());
                    }
                    break;
                case 2:
                    if (!st.getListMarkJavaCore().isEmpty()) {
                        do {
                            System.out.println("Điểm Java Core");
                            for (int i = 0; i < st.getListMarkJavaCore().size(); i++) {
                                System.out.printf(" %s |\n %s |", "lần " + (i + 1), st.getListMarkJavaCore().get(i));
                            }
                            System.out.println("Nhập lần muốn sửa: ");
                            int index = Integer.parseInt(sc.nextLine());
                            if (index <= 0 || st.getListMarkJavaCore().size() < index) {
                                System.err.println("Lần muốn sửa không tồn tại");
                            } else {
                                System.out.println("Nhập điểm số mới");
                                float pointJC = Float.parseFloat(sc.nextLine());
                                st.getListMarkJavaCore().add(index - 1, pointJC);
                                System.out.println("Sửa điểm môn JavaCore thành công !");
                            }
                            System.out.println("Bạn có muốn tiếp tục sửa điểm Java Core (Y/N):");
                            select = sc.nextLine();
                        } while (select.equalsIgnoreCase("Y"));
                    } else {
                        System.err.printf("Sinh viên %s chưa có điểm môn JavaCore.", st.getStudentName());
                    }
                    break;
                case 3:
                    if (!st.getListMarkJavaWeb().isEmpty()) {
                        do {
                            System.out.println("Điểm Java Web");
                            for (int i = 0; i < st.getListMarkJavaWeb().size(); i++) {
                                System.out.printf(" %s |\n %s |", "lần " + (i + 1), st.getListMarkJavaWeb().get(i));
                            }
                            System.out.println("Nhập lần muốn sửa: ");
                            int index = Integer.parseInt(sc.nextLine());
                            if (index <= 0 || st.getListMarkJavaWeb().size() < index) {
                                System.err.println("Lần muốn sửa không tồn tại");
                            } else {
                                System.out.println("Nhập điểm số mới");
                                float pointJW = Float.parseFloat(sc.nextLine());
                                st.getListMarkJavaWeb().add(index - 1, pointJW);
                                System.out.println("Sửa điểm môn JavaWeb thành công !");
                            }
                            System.out.println("Bạn có muốn tiếp tục sửa điểm Java Web (Y/N):");
                            select = sc.nextLine();
                        } while (select.equalsIgnoreCase("Y"));
                    } else {
                        System.err.printf("Sinh viên %s chưa có điểm môn JavaWeb.", st.getStudentName());
                    }
                    break;
                case 4:
                    StudentManagement.updateStudent(st.getStudentId(),sc);
                    break;
                default:
                    System.err.println("Lựa chọn không phù hợp");
            }
            System.out.printf("Bạn có muốn tiếp tục sửa điểm cho sinh viên %s không (Y/N): ", st.getStudentName());
            select = sc.nextLine();
        } while (select.equalsIgnoreCase("Y"));
    }

    public static void sumGpa() {
        int countA = 0, countB = 0, countC = 0, countF = 0, countU = 0;
        Map<String, Integer> gpas = new HashMap<>();
        System.out.printf(" %-15s | %10s |\n", "Xếp Loại", "Số lượng");
        for (Student st : students) {
            switch (st.getGpa()) {
                case "Giỏi":
                    countA++;
                    gpas.put("Giỏi", countA);
                    break;
                case "Khá":
                    countB++;
                    gpas.put("Khá", countB);
                    break;
                case "Trung bình":
                    countC++;
                    gpas.put("Trung bình", countC);
                    break;
                case "Yếu":
                    countF++;
                    gpas.put("Yếu", countF);
                    break;
                default:
                    countU++;
                    gpas.put("Chưa xếp loại", countF);
                    break;
            }
        }
        gpas.forEach((key, value) -> System.out.printf(" %-15s | %10s |\n", key, value));
    }

    public static void sumPass() {
        int countPass = 0;
        for (Student st : students) {
            if (st.getAvgMark() >= 5) {
                countPass++;
            }
        }
        System.out.printf("Số lượng sinh viên qua môn là:  %d sinh viên.\n", countPass);
    }

    public static void sortStudentByAvgMark() {
        students.sort((st1, st2) -> {
            if (st1.getAvgMark() < st2.getAvgMark()) {
                return -1;
            } else if (st1.getAvgMark() == st2.getAvgMark()) {
                return 0;
            } else {
                return 1;
            }
        });
    }

    public static void searchByStudentName(Scanner sc) {
        do {
            System.out.println("Nhập vào tên sinh viên muốn tìm");
            String name = sc.nextLine();
            System.out.printf("%10s | %50s | %10s | %10s | %15s | %15s | %15s | %15s | %8s | %10s | %15s |\n", "ID", "Họ và tên", "Tuổi", "Giới tính", "Lớp", "Điểm JavaScript", "Điểm JavaCore", "Điểm JavaWeb", "Điểm TB", "Loại", "Tình trạng");
            int searchCount = searchByStudentName(name);
            System.out.printf("Đã tìm được %d  sinh viên có tên chứa: %s.\n", searchCount, name);
            System.out.println("Bạn có muốn tiếp tục tìm tên sinh viên khác (Y/N):");
            select = sc.nextLine();
        } while (select.equalsIgnoreCase("Y"));
    }


    //Class method
    public static Student searchByID(String id) {
        for (Student st : students) {
            if (st.getStudentId().equals(id)) {
                return st;
            }
        }
        return null;
    }

    public static int searchByStudentName(String name) {
        int count = 0;
        for (Student st : students) {
            if (st.getStudentName().toLowerCase().contains(name.toLowerCase())) {
                st.displayData();
                count++;
            }
        }
        return count;
    }

}
