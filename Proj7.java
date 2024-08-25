package com.placement;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int rollnum;
    int sslcMark;
    int hscMark;
    int cgpa;

    public Student(String name, int rollnum,int sslcMark, int hscMark, int cgpa) {
        this.name = name;
        this.rollnum =rollnum;
        this.sslcMark = sslcMark;
        this.hscMark = hscMark;
        this.cgpa = cgpa;
    }
}

class Staff extends Student {
    public int placementMark;
	public int trainingMark;

	public Staff(String name,int rollnum, int sslcMark, int hscMark, int cgpa,int placementMark,int trainingMark) {
        super(name,rollnum, sslcMark, hscMark, cgpa);
       
        this.placementMark=placementMark;
        this.trainingMark=trainingMark;
    }

    public boolean isQualified() {
        return (sslcMark > 60 && hscMark > 60 && cgpa > 60);
    }
}

class Placement {
    ArrayList<Staff> selectedStudents = new ArrayList<>();
    ArrayList<Staff> rejectedStudents = new ArrayList<>();

    public void filterStudents(ArrayList<Staff> staffList) {
        for (Staff staff : staffList) {
            if (staff.isQualified()) {
                selectedStudents.add(staff);
            } else {
                rejectedStudents.add(staff);
            }
        }
    }

    public void conductTest() {
        ArrayList<Staff> batch1 = new ArrayList<>();
        ArrayList<Staff> batch2 = new ArrayList<>();

        for (Staff student : selectedStudents) {
            if (student.placementMark > 50) {
                batch1.add(student);
            } else {
                batch2.add(student);
            }
        }

        //System.out.println("Number of students selected for placement: " + batch1.size());
        System.out.println("Batch 1:");
        System.out.println("Name\tRollnum\tCGPA\tPlacementmark");
        for (Staff student : batch1) {
            System.out.println(student.name + "\t" + student.rollnum + "\t" + student.cgpa + "\t" + student.placementMark);
        }

        System.out.println("Trainingmark\tStatus");
        for (Staff student : batch1) {
            if (student.trainingMark > 70) {
                System.out.println(student.trainingMark + "\tSelected");
            } else {
                System.out.println(student.trainingMark + "\tMoved to batch 2");
            }
        }

        System.out.println("Batch 2:");
        System.out.println("Name\tRollnum\tCGPA\tPlacementmark");
        for (Staff student : batch2) {
            System.out.println(student.name + "\t" + student.rollnum + "\t" + student.cgpa + "\t" + student.placementMark);
        }

        System.out.println("Trainingmark\tStatus");
        for (Staff student : batch2) {
            if (student.trainingMark > 70) {
                System.out.println(student.trainingMark + "\tMoved to batch 1");
            } else {
                System.out.println(student.trainingMark + "\tRetained in batch 2");
            }
        }
    }
}

public class Proj7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Staff> staffList = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int numStudents = sc.nextInt();
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.next();
            System.out.println("Rollnum: ");
            int rollnum=sc.nextInt();
            System.out.print("10th Mark: ");
            int sslcMark = sc.nextInt();
      
            System.out.print("12th Mark: ");
            int hscMark = sc.nextInt();
            System.out.print("CGPA: ");
            int cgpa = sc.nextInt();
            int placementMark,trainingMark;
            if(sslcMark<60 || hscMark<60 || cgpa<60)
            { System.out.println("Not Eligile for placement");
              placementMark=0;
              trainingMark=0;
              
              
            }
            else {
            System.out.print("Placement Mark: ");
            placementMark = sc.nextInt();
            System.out.print("Training Mark: ");
            trainingMark = sc.nextInt();
            }
            staffList.add(new Staff(name,rollnum, sslcMark, hscMark, cgpa,placementMark, trainingMark));
        }

        Placement placement = new Placement();
        placement.filterStudents(staffList);
        placement.conductTest();
        sc.close();
    }
}

