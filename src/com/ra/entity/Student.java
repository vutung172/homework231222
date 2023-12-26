package com.ra.entity;


import com.ra.run.AcademyManagement;
import com.ra.run.ClassManagement;
import com.ra.run.StudentManagement;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student implements IStudentManagement, IValid{
    private String studentId;
    private String studentName;
    private int age;
    private boolean sex;
    private StudentClass studentClass;
    private ArrayList<Float> listMarkJavaScript = new ArrayList<>();
    private ArrayList<Float> listMarkJavaCore = new ArrayList<>();
    private ArrayList<Float> listMarkJavaWeb = new ArrayList<>();
    private float avgMark;
    private String gpa;
    private boolean studentStatus;

    public Student() {
    }

    public Student(String studentId, String studentName, int age, boolean sex, StudentClass studentClass, ArrayList<Float> listMarkJavaScript, ArrayList<Float> listMarkJavaCore, ArrayList<Float> listMarkJavaWeb, float avgMark, String gpa, boolean studentStatus) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.sex = sex;
        this.studentClass = studentClass;
        this.listMarkJavaScript = listMarkJavaScript;
        this.listMarkJavaCore = listMarkJavaCore;
        this.listMarkJavaWeb = listMarkJavaWeb;
        this.avgMark = avgMark;
        this.gpa = gpa;
        this.studentStatus = studentStatus;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public ArrayList<Float> getListMarkJavaScript() {
        return listMarkJavaScript;
    }

    public void setListMarkJavaScript(ArrayList<Float> listMarkJavaScript) {
        this.listMarkJavaScript = listMarkJavaScript;
    }

    public ArrayList<Float> getListMarkJavaCore() {
        return listMarkJavaCore;
    }

    public void setListMarkJavaCore(ArrayList<Float> listMarkJavaCore) {
        this.listMarkJavaCore = listMarkJavaCore;
    }

    public ArrayList<Float> getListMarkJavaWeb() {
        return listMarkJavaWeb;
    }

    public void setListMarkJavaWeb(ArrayList<Float> listMarkJavaWeb) {
        this.listMarkJavaWeb = listMarkJavaWeb;
    }

    public float getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(float avgMark) {
        this.avgMark = avgMark;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public boolean isStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(boolean studentStatus) {
        this.studentStatus = studentStatus;
    }

    @Override
    public void inputData(Scanner sc) {
        String select;
        //Nhập mã sinh viên, kiểm tra mã đúng quy định, kiểm tra mã đã tồn tại trong list
        inputStudentID(sc);
        //Nhập tên sinh viên, kiểm tra tên có đúng quy định
        inputStudentName(sc);
        //Nhập vào tuổi, kiểm tra tuổi đúng quy định
        inputAge(sc);
        //Nhập giới tính
        inputSex(sc);
        //Xác nhận có muốn thêm lớp hay không
        System.out.println("Bạn có muốn cho lớp cho sinh viên (Y/N): ");
        select = sc.nextLine();
        if (select.equalsIgnoreCase("Y")) {
            if (!AcademyManagement.getClasses().isEmpty()) {
                //Nhập lớp
                inputClass(sc);
                //Xác nhận có muốn nhập điểm hay không
                System.out.println("Bạn có muốn cho lớp cho sinh viên (Y/N): ");
                select = sc.nextLine();
                //Nhập điểm
                if (select.equalsIgnoreCase("Y")){
                    inputMark(sc);
                }
            } else {
                System.err.println("Chưa có lớp nào để chọn");
            }
        }
        //Nhập Trạng thái sinh viên
        inputStudentStatus(sc);
    }

    //Method input:
    public void inputStudentID(Scanner sc) {
        StudentManagement students = new StudentManagement();
        do {
            System.out.println("Nhập vào mã sinh viên: ");
            this.studentId = sc.nextLine();
            if (!isValid("S.{5}", this.studentId)) {
                System.err.println("Mã sinh viên không hợp lệ");
            } else if (students.searchByID(this.studentId) != null) {
                System.err.println("Mã sinh viên đã tồn tại, mời nhập mã khác");
            } else {
                break;
            }
        } while (true);
    }
    public void inputStudentName(Scanner sc){
        do {
            System.out.println("Nhập tên sinh viên:");
            String name = sc.nextLine();
            if (!isValid(".{20,50}", name)) {
                System.err.println("Tên sinh viên không hợp lệ (gồm 20 đến 50 kí tự)");
            } else {
                this.studentName = name;
                break;
            }
        } while (true);
    }
    public void inputAge(Scanner sc){
        do {
            System.out.println("Nhâp vào tuổi: ");
            int age = Integer.parseInt(sc.nextLine());
            if (age < 18) {
                System.err.println("Độ tuổi không phù hợp (!Phải từ 18 tuổi trở lên)");
            } else {
                this.age = age;
                break;
            }
        } while (true);
    }
    public void inputSex(Scanner sc){
        do {
            System.out.println("Nhập vào giới tính: ");
            System.out.println("1. Nam");
            System.out.println("2. Nữ");
            System.out.print("Mời lựa chọn (1-2): ");
            int sex = Integer.parseInt(sc.nextLine());
            if (sex == 1){
                this.sex = true;
                break;
            } else if (sex == 2) {
                this.sex = false;
                break;
            } else {
                System.err.println("Lựa chọn không phù hợp, mời chọn lại");
            }
        } while (true);
    }
    public void inputClass(Scanner sc){
        ClassManagement.showClass();
        do {
            System.out.println("Nhập vào mã lớp bạn muốn chọn:");
            String classId = sc.nextLine();
            StudentClass cl = ClassManagement.searchClassByID(classId);
            if (cl == null) {
                System.err.println("Lớp bạn chọn không tồn tại, mời nhập lại");
            } else {
                this.studentClass = cl;
                break;
            }
        } while (true);
    }

    public void inputMark(Scanner sc){
        String select;
        do {
            System.out.println("Chọn môn học muốn nhập điểm:");
            System.out.println("1. Java Script");
            System.out.println("2. Java Core");
            System.out.println("3. Jav Web");
            System.out.println("4. Thoát");
            System.out.print("Mời lựa chọn (1-4): ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    do {
                        System.out.println("Nhập vào điểm môn Java Script: ");
                        float pointJS = Float.parseFloat(sc.nextLine());
                        this.listMarkJavaScript.add(pointJS);
                        System.out.println("Nhập điểm môn JavaScript thành công !");
                        System.out.print("Bạn có muốn tiếp tục nhập điểm môn JavaScript (Y/N): ");
                        select = sc.nextLine();
                    } while (select.equalsIgnoreCase("Y"));
                    break;
                case 2:
                    do {
                        System.out.println("Nhập vào điểm môn Java Core");
                        float pointJC = Float.parseFloat(sc.nextLine());
                        listMarkJavaCore.add(pointJC);
                        System.out.println("Nhập điểm môn JavaCore thành công !");
                        System.out.print("Bạn có muốn tiếp tục nhập điểm môn Java Core (Y/N): ");
                        select = sc.nextLine();
                    } while (select.equalsIgnoreCase("Y"));
                    break;
                case 3:
                    do {
                        System.out.println("Nhập vào điểm môn Java Web");
                        float pointJW = Float.parseFloat(sc.nextLine());
                        listMarkJavaWeb.add(pointJW);
                        System.out.println("Nhập điểm môn JavaWeb thành công !");
                        System.out.print("Bạn có muốn tiếp tục nhập điểm môn Java Web (Y/N): ");
                        select = sc.nextLine();
                    } while (select.equalsIgnoreCase("Y"));
                    break;
                case 4:
                    StudentManagement.updateStudent(this.getStudentId(),sc);
                    break;
                default:
                    System.err.println("Lựa chọn môn học không phù hợp");
            }
            System.out.printf("Bạn có muốn tiếp tục nhập điểm cho sinh viên %s không (Y/N):",this.studentName);
            select = sc.nextLine();
        } while (select.equalsIgnoreCase("Y"));
    }
    public void inputStudentStatus(Scanner sc){
        do {
            System.out.println("Nhập vào trạng thái sinh viên: ");
            System.out.println("1. Đang học");
            System.out.println("2. Đã nghỉ");
            System.out.print("Lựa cho của bạn (1-2): ");
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1){
                this.studentStatus = true;
                break;
            } else if (choice == 2) {
                this.studentStatus = false;
                break;
            } else {
                System.err.println("Lựa chọn không phù hợp, mời chọn lại");
            }
        }while (true);
    }

    @Override
    public void displayData() {
        System.out.printf("%10s | %50s | %10s | %10s | %15s | %15s | %15s | %15s | %8.2f | %10s | %15s |\n",
                getStudentId(),
                getStudentName(),
                getAge(), isSex() ? "Nam" : "Nữ",
                (getStudentClass() == null) ? "Chưa xếp lớp" : getStudentClass().getClassName(),
                (getListMarkJavaScript().isEmpty()) ? "Chưa có điểm" : listMarkJavaScript.get(listMarkJavaScript.size() - 1),
                (getListMarkJavaCore().isEmpty()) ? "Chưa có điểm" : listMarkJavaCore.get(listMarkJavaCore.size() - 1),
                (getListMarkJavaWeb().isEmpty()) ? "Chưa có điểm" : listMarkJavaWeb.get(listMarkJavaWeb.size() - 1),
                getAvgMark(),
                getGpa(),
                isStudentStatus() ? "Đang học" : "Đã nghỉ");
    }


    //Method class
    public void calAvgMark() {
        if (!listMarkJavaScript.isEmpty()) {
            if (!listMarkJavaCore.isEmpty()) {
                if (!listMarkJavaWeb.isEmpty()) {
                    this.avgMark = (listMarkJavaScript.get(listMarkJavaScript.size() - 1) + listMarkJavaCore.get(listMarkJavaCore.size() - 1) + listMarkJavaWeb.get(listMarkJavaWeb.size() - 1)) / 3;
                } else {
                    System.err.println(this.studentName + ": Chưa có điểm JavaWeb");
                }
            } else {
                System.err.println(this.studentName + ": Chưa có điểm Java Core");
            }
        } else {
            System.err.println(this.studentName + ": Chưa có điểm Java Script");
        }
    }

    public void getGPA() {
        if (avgMark == 0) {
            setGpa("Chưa xếp loại");
        } else if (0 < avgMark && avgMark < 5) {
            setGpa("Yếu");
        } else if (5 <= avgMark && avgMark < 7) {
            setGpa("Trung bình");
        } else if (7 <= avgMark && avgMark < 9) {
            setGpa("Khá");
        } else {
            setGpa("Giỏi");
        }
    }

    @Override
    public <T, O> boolean isValid(T t, O obj) {
        Pattern p = Pattern.compile((String) t);
        Matcher m = p.matcher((CharSequence) obj);
        return m.matches();
    }

}
