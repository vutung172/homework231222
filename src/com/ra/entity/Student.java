package com.ra.entity;


import com.ra.run.AcademyManagement;
import com.ra.run.ClassManagement;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student implements IStudentManagement,IValid,ISearch{
    private String studentId;
    private String studentName;
    private int age;
    private boolean sex;
    private StudentClass studentClass;
    private ArrayList<Float> listMarkJavaScript = new ArrayList<>();
    private ArrayList<Float> listMarkJavaCore= new ArrayList<>();
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
        int count;
        do {
            count = 0;
            System.out.println("Nhập vào mã sinh viên; ");
            this.studentId = sc.nextLine();
            if (!isValid("S.{5}",this.studentId)){
                System.err.println("Mã sinh viên không hợp lệ");
            }
            for (Student s:AcademyManagement.getStudents()) {
                if (s.getStudentId().equals(this.studentId)){
                    System.err.println("Mã sinh viên đã tồn tại, mời nhập mã khác");
                    count++;
                    break;
                }
            }
        } while (!isValid("S.{5}",this.studentId) || count != 0);
        do {
            System.out.println("Nhập tên sinh viên:");
            this.studentName = sc.nextLine();
            if (!isValid(".{20,50}",this.studentName))
                System.err.println("Tên sinh viên không hợp lệ (gồm 20 đến 50 kí tự)");

        } while (!isValid(".{20,50}",this.studentName));
        do {
            System.out.println("Nhâp vào tuổi: ");
            this.age = Integer.parseInt(sc.nextLine());
            if (this.age < 18){
                System.err.println("Độ tuổi không phù hợp (!Phải từ 18 tuổi trở lên)");
            }
        } while (this.age <18 );
        System.out.println("Nhập vào giới tính: ");
        this.sex = Boolean.parseBoolean(sc.nextLine());
        System.out.println("Nhập vào trạng thái sinh viên: ");
        this.studentStatus = Boolean.parseBoolean(sc.nextLine());
        System.out.println("Bạn có muốn cho lớp cho sinh viên (Y/N): ");
        select = sc.nextLine();
        if (select.equalsIgnoreCase("Y")){
            AcademyManagement.getClasses().forEach(c -> {
                System.out.printf("%5s | %15s | %30s | %10s |\n", "ID", "Tên lớp", "Mô tả", "Trạng thái");
                c.displayData();
            });
            do {
                System.out.println("Nhập vào mã lớp bạn muốn chọn:");
                select = sc.nextLine();
                for (StudentClass c:AcademyManagement.getClasses()) {
                    if (c.searchByID(select) != null){
                        this.studentClass = c;
                        count++;
                        break;
                    }
                }
                if (count == 0){
                    System.err.println("Lớp bạn chọn không tồn tại, mời nhập lại");
                }
            } while(count == 0);
            do {
                System.out.println("Nhập vào điểm môn Java Script: ");
                float pointJS = Float.parseFloat(sc.nextLine());
                this.listMarkJavaScript.add(pointJS);
                System.out.println("Bạn có muốn tiếp tục (Y/N):");
                select = sc.nextLine();
            } while (select.equalsIgnoreCase("Y"));
            do {
                System.out.println("Nhập vào điểm môn Java Core");
                float pointJC = Float.parseFloat(sc.nextLine());
                listMarkJavaCore.add(pointJC);
                System.out.println("Bạn có muốn tiếp tục (Y/N):");
                select = sc.nextLine();
            } while (select.equalsIgnoreCase("Y"));
            do {
                System.out.println("Nhập vào điểm môn Java Web");
                float pointJW = Float.parseFloat(sc.nextLine());
                listMarkJavaWeb.add(pointJW);
                System.out.println("Bạn có muốn tiếp tục (Y/N):");
                select = sc.nextLine();
            } while (select.equalsIgnoreCase("Y"));
        }
    }

    @Override
    public void displayData() {
        System.out.printf("%10s | %20s | %10s | %10s | %10s | %10s | %10s | %10s | %10s | %10s | %15s |\n",getStudentId(),getStudentName(),getAge(),isSex()?"Nam":"Nữ",getStudentClass(),getListMarkJavaScript().getLast(),getListMarkJavaCore().getLast(),getListMarkJavaWeb().getLast(),getAvgMark(),getGpa(),isStudentStatus()?"Đang hoạt động":"Thôi học");
    }

    public void calAvgMark(){
        if (!listMarkJavaScript.isEmpty()){
            if (!listMarkJavaCore.isEmpty()){
                if (!listMarkJavaWeb.isEmpty()){
                    this.avgMark = (listMarkJavaScript.getLast()+listMarkJavaCore.getLast()+listMarkJavaWeb.getLast())/3;
                } else {
                    System.out.println(this.studentName+": Chưa có điểm JavaWeb");
                }
            } else {
                System.out.println(this.studentName+": Chưa có điểm Java Core");
            }
        } else {
            System.out.println(this.studentName+": Chưa có điểm Java Script");
        }
    }

    public String getGPA(){
        if (0 <= avgMark && avgMark < 5){
            setGpa("Yếu");
        } else if (5 <= avgMark && avgMark <7) {
            setGpa("Trung bình");
        } else if (7 <= avgMark && avgMark <9) {
            setGpa("Khá");
        } else {
            setGpa("Giỏi");
        }
        return getGpa();
    }

    @Override
    public <T,O> boolean isValid(T t,O obj) {
        Pattern p = Pattern.compile((String) t);
        Matcher m = p.matcher((CharSequence) obj);
        return m.matches();
    }


    @Override
    public Student searchByID(String id) {
        if (this.studentId.equals(id))
            return this;
        return null;
    }

    @Override
    public void searchByString(String string) {

    }
}
