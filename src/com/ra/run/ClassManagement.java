package com.ra.run;

import com.ra.entity.ISearch;
import com.ra.entity.Student;
import com.ra.entity.StudentClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ClassManagement {
    private static String select;
    private static int choice;
    public static List<StudentClass> classes = AcademyManagement.getClasses();
    public static Map<String, Integer> listActive = new HashMap<>();

    public static List<StudentClass> getClasses() {
        return classes;
    }

    public static void setClasses(List<StudentClass> classes) {
        ClassManagement.classes = classes;
    }

    public static void displayData(Scanner sc) {
        do {

            System.out.println("**********************QUẢN LÝ LỚP HỌC********************");
            System.out.println("1. Thêm mới lớp học");
            System.out.println("2. Cập nhật thông tin lớp học");
            System.out.println("3. Hiển thị thông tin lớp học");
            System.out.println("4. Thống kê các lớp học đang hoạt động");
            System.out.println("5. Tìm kiếm lớp học theo tên lớp học");
            System.out.println("6. Thoát");
            System.out.print("Mời bạn nhập lựa chọn (1-6): ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addClass(sc);
                    break;
                case 2:
                    updateClass(sc);
                    break;
                case 3:
                    showClass();
                    break;
                case 4:
                    calActive();
                    break;
                case 5:
                    searchClassByName(sc);
                    break;
                case 6:
                    AcademyManagement.display();
                    break;
                default:
                    System.out.println("Lựa chọn của bạn không phù hợp");
            }

        } while (true);
    }

    //Class function:
    public static void addClass(Scanner sc){
        do {
            StudentClass studentClass = new StudentClass();
            studentClass.inputData(sc);
            classes.add(studentClass);
            System.out.println("Thêm thành công");
            AcademyManagement.writeListClass();
            System.out.println("Bạn có muốn tiếp tục (Y/N):");
            select = sc.nextLine();
        } while (select.equalsIgnoreCase("Y"));
    }

    public static void updateClass(Scanner sc) {
        do {
            System.out.println("Nhập vào ID lớp bạn muốn cập nhật");
            String id = sc.nextLine();
            StudentClass cl = searchClassByID(id);
            if (cl != null){
                do {
                    System.out.printf("%5s | %15s | %30s | %10s |\n", "ID", "Tên lớp", "Mô tả", "Trạng thái");
                    cl.displayData();
                    System.out.println("Chọn mục bạn muốn cập nhật:");
                    System.out.println("1. Tên lớp");
                    System.out.println("2. Mô tả lớp");
                    System.out.println("3. Trạng thái lớp");
                    System.out.println("4. Thoát");
                    System.out.print("Mời lựa chọn (1-4): ");
                    choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            cl.inputClassName(sc);
                            System.out.println("Cập nhật tên lớp thành công");
                            break;
                        case 2:
                            cl.inputDescription(sc);
                            System.out.println("Cập nhật mô tả lớp thành công");
                            break;
                        case 3:
                            cl.inputClassStatus(sc);
                            System.out.println("Cập nhật tình trạng lớp thành công");
                            break;
                        case 4:
                            ClassManagement.displayData(sc);
                            break;
                        default:
                            System.err.println("Lựa chọn không phù hợp");
                    }
                    System.out.printf("Bạn có muốn tiếp tục cập nhật cho lớp %s (Y/N):",cl.getClassName());
                    select = sc.nextLine();
                } while (select.equalsIgnoreCase("Y"));
            } else {
                System.err.println("ID lớp không tồn tại");
            }
            System.out.println("Bạn có muốn cập nhật lớp khác (Y/N):");
            select = sc.nextLine();
        } while (select.equalsIgnoreCase("Y"));

    }
    public static void showClass(){
        System.out.printf("%5s | %15s | %30s | %10s |\n", "ID", "Tên lớp", "Mô tả", "Trạng thái");
        classes.forEach(StudentClass::displayData);
    }
    public static void calActive(){
        int waiting = 0, active = 0, pause = 0, closed = 0;
        for (StudentClass c : classes) {
            switch (c.getClassStatus()) {
                case 0:
                    waiting++;
                    listActive.put("Waiting", waiting);
                    break;
                case 1:
                    active++;
                    listActive.put("Active", active);
                    break;
                case 2:
                    pause++;
                    listActive.put("Pause", pause);
                    break;
                default:
                    closed++;
                    listActive.put("Closed", closed);
                    break;
            }
        }
        System.out.printf("%-15s | %-10s |\n", "Tình trạng lớp", "Số lượng");
        System.out.printf("%-15s + %-10s |\n", "---------------", "----------");
        System.out.printf("%-15s | %-10s |\n", "Đang chờ lớp", listActive.get("Waiting"));
        System.out.printf("%-15s + %-10s |\n", "---------------", "----------");
        System.out.printf("%-15s | %-10s |\n", "Lớp hoạt động", listActive.get("Active"));
        System.out.printf("%-15s + %-10s |\n", "---------------", "----------");
        System.out.printf("%-15s | %-10s |\n", "Lớp tạm dừng", listActive.get("Pause"));
        System.out.printf("%-15s + %-10s |\n", "---------------", "----------");
        System.out.printf("%-15s | %-10s |\n", "Lớp đã đóng", listActive.get("Closed"));
        System.out.printf("%-15s + %-10s |\n", "---------------", "----------");
    }

    public static void searchClassByName(Scanner sc){
        do {
            System.out.println("Nhập vào tên lớp muốn tìm: ");
            String searchClassName = sc.nextLine();
            System.out.printf("%5s | %15s | %30s | %10s |\n", "ID", "Tên lớp", "Mô tả", "Trạng thái");
            System.out.printf("%5s + %15s + %30s + %10s |\n", "-----", "----------------", "------------------------------", "----------");
            int searchNumber = searchClassByName(searchClassName);
            System.out.printf("Đã tìm được %d lớp có chứa từ tên là: %s\n",searchNumber,searchClassName);
            System.out.println("Bạn có muốn tiếp tục tìm theo tên lớp khác(Y/N):");
            select = sc.nextLine();
        } while (select.equalsIgnoreCase("Y"));
    }


    //Class method
    public static StudentClass searchClassByID(String id){
        for (StudentClass c: classes) {
            if (c.getClassId().equals(id)){
                return c;
            }
        }
        return null;
    }
    public static int searchClassByName(String name){
        int count = 0;
        for (StudentClass cl: classes) {
            if (cl.getClassName().toLowerCase().contains(name.toLowerCase())){
                cl.displayData();
                count ++;
            }
        }
        return count;
    }
}
