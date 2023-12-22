package com.ra.run;

import com.ra.entity.StudentClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ClassManagement {

    public static  void displayData() {
        List<StudentClass> classes = AcademyManagement.getClasses();
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("**********************QUẢN LÝ LỚP HỌC********************");
            System.out.println("1. Thêm mới lớp học");
            System.out.println("2. Cập nhật thông tin lớp học");
            System.out.println("3. Hiển thị thông tin lớp học");
            System.out.println("4. Thống kê các lớp học đang hoạt động");
            System.out.println("5. Tìm kiếm lớp học theo tên lớp học");
            System.out.println("6. Thoát");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    StudentClass studentClass = new StudentClass();
                    studentClass.inputData();
                    classes.add(studentClass);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.printf("%5s | %15s | %30s | %10s |\n","ID","Tên lớp","Mô tả","Trạng thái");
                    classes.forEach(StudentClass::displayData);
                    break;
                case 4:
                    Map<String,Integer> listActice = new HashMap<>();
                    classes.forEach( c -> {
                        if (c.getClassStatus() == 2){
                            listActice.put("Active",+1);
                        } else if (c.getClassStatus() == 3){
                            listActice.put("Inactive",+1);
                        }
                    });
                    System.out.println("Số lớp đang hoạt động: "+listActice.get("Active"));
                    System.out.println("Số lớp không hoạt động: "+listActice.get("Inactive"));
                    break;
                case 5:
                    String select;
                    do {
                        System.out.println("Nhập vào tên lớp muốn tìm: ");
                        String searchClass = sc.nextLine();
                        System.out.printf("%5s | %15s | %30s | %10s |\n","ID","Tên lớp","Mô tả","Trạng thái");
                        classes.forEach(c -> {
                            if (c.getClassName().contains(searchClass)){
                                c.displayData();
                            }
                        });
                        System.out.println("Bạn có muốn tiếp tục");
                        select = sc.nextLine();
                    } while (select.equals("Y"));
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn của bạn không phù hợp");
            }

        } while (true);
    }

    public void updateData(Scanner sc){
        System.out.println("Nhập vào ID lớp bạn muốn cập nhật");

    }

}
