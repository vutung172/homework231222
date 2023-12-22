package com.ra.run;

import com.ra.entity.IStudentManagement;

import java.util.Scanner;

public class StudentManagement implements IStudentManagement {

    @Override
    public void inputData() {
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("***********************QUẢN LÝ SINH VIÊN******************");
            System.out.println("1. Thêm mới sinh viên");
            System.out.println("2. Cập nhật thông tin sinh viê");
            System.out.println("3. Hiển thị thông tin sinh viên");
            System.out.println("4. Tính điểm trung bình");
            System.out.println("5. Xếp loại sinh viên");
            System.out.println("6. Sắp xếp sinh viên theo điểm trung bình tăng dần");
            System.out.println("7. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("8. Thống kê số sinh viên đạt loại giỏi, khá, trung bình và yếu");
            System.out.println("9. Thống kê các sinh viên Pass qua các môn học");
            System.out.println("10.Thoát");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn của bạn không phù hợp");
            }

        } while (true);
    }

    @Override
    public void displayData() {

    }
}
