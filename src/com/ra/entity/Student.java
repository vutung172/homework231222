package com.ra.entity;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student implements IStudentManagement,IValid{
    private String studentId;
    private String studentName;
    private int age;
    private boolean sex;
    private StudentClass studentClass;
    private ArrayList<Float> listMarkJavaScript;
    private ArrayList<Float> listMarkJavaCore;
    private ArrayList<Float> listMarkJavaWeb;
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
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào mã sinh viên; ");
        studentId = sc.nextLine();
        while (!isValid("S.{5}",studentId)){
            System.err.println("mã sinh viên không hợp lệ");
            studentId = sc.nextLine();
        }
        System.out.println("Nhập tên sinh viên:");
        studentName = sc.nextLine();
        while (!isValid(".{20,50}",studentName)){
            System.err.println("Tên sinh viên không hợp lệ");
        }
        System.out.println("Nhâp vào tuổi: ");
        age = Integer.parseInt(sc.nextLine());
        while (age < 18){
            System.err.println("Độ tuổi không cho phép");
        }
        System.out.println("Nhập vào giới tính: ");
        sex = Boolean.parseBoolean(sc.nextLine());
        System.out.println("Nhập vào lớp: ");
        // tim lớp
        System.out.println("Nhập vào điểm môn Java Script: ");
        float pointJS = Float.parseFloat(sc.nextLine());
        listMarkJavaScript.add(pointJS);
        System.out.println("Nhập vào điểm môn Java Core");
        float pointJC = Float.parseFloat(sc.nextLine());
        listMarkJavaCore.add(pointJC);
        System.out.println("Nhập vào điểm môn Java Web");
        float pointJW = Float.parseFloat(sc.nextLine());
        listMarkJavaWeb.add(pointJW);
    }

    @Override
    public void displayData() {
        System.out.printf("%s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s |\n",getStudentId(),getStudentName(),getAge(),isSex()?"Nam":"Nữ",getStudentClass(),getListMarkJavaScript().getLast(),getListMarkJavaCore().getLast(),getListMarkJavaWeb().getLast(),getAvgMark(),getGpa(),isStudentStatus()?"Đang hoạt động":"Đã nghỉ học");
    }

    public float calAvgMark(){
        return avgMark = (listMarkJavaScript.getLast()+listMarkJavaCore.getLast()+listMarkJavaWeb.getLast())/3;
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

    public <T, Scanner> void  inputMark(T subject, Scanner sc){

    }

}
