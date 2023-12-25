package com.ra.run;

import com.ra.entity.StudentClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ClassManagement {
    static List<StudentClass> classes = AcademyManagement.getClasses();
    static Map<String, Integer> listActice = new HashMap<>();

    public static List<StudentClass> getClasses() {
        return classes;
    }

    public static void setClasses(List<StudentClass> classes) {
        ClassManagement.classes = classes;
    }

    public static void displayData(Scanner sc) {
        String select;
        do {

            System.out.println("**********************QUẢN LÝ LỚP HỌC********************");
            System.out.println("1. Thêm mới lớp học");
            System.out.println("2. Cập nhật thông tin lớp học");
            System.out.println("3. Hiển thị thông tin lớp học");
            System.out.println("4. Thống kê các lớp học đang hoạt động");
            System.out.println("5. Tìm kiếm lớp học theo tên lớp học");
            System.out.println("6. Thoát");
            System.out.println("Mời bạn nhập lựa chọn (1-6)");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    do {
                        StudentClass studentClass = new StudentClass();
                        studentClass.inputData(sc);
                        classes.add(studentClass);
                        System.out.println("Thêm thành công");
                        System.out.println("Bạn có muốn tiếp tục (Y/N):");
                        select = sc.nextLine();
                    } while (select.equalsIgnoreCase("Y"));
                    break;
                case 2:
                    updateClass(sc);
                    break;
                case 3:
                    System.out.printf("%5s | %15s | %30s | %10s |\n", "ID", "Tên lớp", "Mô tả", "Trạng thái");
                    classes.forEach(StudentClass::displayData);
                    break;
                case 4:
                    int wait = 0, active = 0, pause = 0, closed = 0;
                    for (StudentClass c : classes) {
                        switch (c.getClassStatus()) {
                            case 0:
                                wait++;
                                listActice.put("Waiting", wait);
                                break;
                            case 1:
                                active++;
                                listActice.put("Active", active);
                                break;
                            case 2:
                                pause++;
                                listActice.put("Pause", pause);
                                break;
                            default:
                                closed++;
                                listActice.put("Closed", closed);
                                break;
                        }
                    }
                    System.out.printf("%-15s + %-10s |\n", "---------------", "----------");
                    System.out.printf("%-15s | %-10s |\n", "Tình trạng lớp", "Số lượng");
                    System.out.printf("%-15s + %-10s |\n", "---------------", "----------");
                    System.out.printf("%-15s | %-10s |\n", "Đang chờ lớp", listActice.get("Waiting"));
                    System.out.printf("%-15s + %-10s |\n", "---------------", "----------");
                    System.out.printf("%-15s | %-10s |\n", "Lớp hoạt động", listActice.get("Active"));
                    System.out.printf("%-15s + %-10s |\n", "---------------", "----------");
                    System.out.printf("%-15s | %-10s |\n", "Lớp tạm dừng", listActice.get("Pause"));
                    System.out.printf("%-15s + %-10s |\n", "---------------", "----------");
                    System.out.printf("%-15s | %-10s |\n", "Lớp đã đóng", listActice.get("Closed"));
                    System.out.printf("%-15s + %-10s |\n", "---------------", "----------");
                    break;
                case 5:
                    do {
                        System.out.println("Nhập vào tên lớp muốn tìm: ");
                        String searchClass = sc.nextLine();
                        System.out.printf("%5s | %15s | %30s | %10s |\n", "ID", "Tên lớp", "Mô tả", "Trạng thái");
                        System.out.printf("%5s + %15s + %30s + %10s |\n", "-----", "----------------", "------------------------------", "----------");
                        classes.forEach(c -> {
                            if (c.getClassName().contains(searchClass)) {
                                c.displayData();
                            }
                        });
                        System.out.println("Bạn có muốn tiếp tục(Y/N):");
                        select = sc.nextLine();
                    } while (select.equalsIgnoreCase("Y"));
                    break;
                case 6:
                    AcademyManagement.display();
                    break;
                default:
                    System.out.println("Lựa chọn của bạn không phù hợp");
            }

        } while (true);
    }

    public static void updateClass(Scanner sc) {
        String select;
        do {
            System.out.println("Nhập vào ID lớp bạn muốn cập nhật");
            String id = sc.nextLine();
            int count = 0;
            for (StudentClass c : classes) {
                if (c.searchByID(id) != null) {
                    count++;
                    do {
                        String string;
                        c.displayData();
                        System.out.println("Chọn mục bạn muốn cập nhật:");
                        System.out.println("1. Tên lớp");
                        System.out.println("2. Mô tả lớp");
                        System.out.println("3. Trạng thái lớp");
                        System.out.println("4. Thoát");
                        int choice = Integer.parseInt(sc.nextLine());
                        switch (choice) {
                            case 1:
                                System.out.println("Nhập tên lớp ,muốn thay đổi:");
                                string = sc.nextLine();
                                c.setClassName(string);
                                break;
                            case 2:
                                System.out.println("Nhập mô tả muốn thay đổi:");
                                string = sc.nextLine();
                                c.setDescription(string);
                                break;
                            case 3:
                                System.out.println("Nhập tình trạng lớp muốn thay đổi: ");
                                int status = Integer.parseInt(sc.nextLine());
                                c.setClassStatus(status);
                                break;
                            case 4:
                                ClassManagement.displayData(sc);
                                break;
                            default:
                                System.out.println("Lựa chọn không phù hợp");
                        }
                        System.out.println("Bạn có muốn tiếp tục (Y/N):");
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


}
