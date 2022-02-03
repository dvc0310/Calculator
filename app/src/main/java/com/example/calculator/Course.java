package com.example.calculator;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.HashMap;


public class Course {
    private final HashMap<String, Double[]> assignmentList = new HashMap<String, Double[]>();
    private final ArrayList<Double> assignmentVal = new ArrayList<Double>();
    private double courseSum = 0.0;
    private final String courseName;
    private final String year;
    private final String season;
    private HashMap<String, Double> cutoffs = new HashMap<String, Double>();
    private double[] def = {60.0, 67.5, 70.0, 73, 77.5, 80.0, 83, 87.5, 90.0, 93};
    private static final String[] letter_grades;


    static {
        letter_grades = new String[]{"D", "D+", "C-", "C", "C+", "B-", "B", "B+", "A-", "A"};
    }

    public Course(String courseName, String year, String season) {
        this.courseName = courseName;
        this.year = year;
        this.season = season;
        for (int i = 0; i < def.length; i++) {
            cutoffs.put(letter_grades[i], def[i]);
        }
    }

    public void changeCutoffs(){

    }

    public String printHash(){
        StringBuilder str = new StringBuilder();
        for (String name: assignmentList.keySet()) {
            str.append(name).append(" ");
            str.append(Objects.requireNonNull(assignmentList.get(name))[0]).append(" ")
                    .append(Objects.requireNonNull(assignmentList.get(name))[1]).append("\n");
        }

        return str.toString();
    }

    private double getPercentSum(){
        double percentSum = 0.0;

        for(String assignment : assignmentList.keySet()){
            percentSum += Objects.requireNonNull(assignmentList.get(assignment))[1];
        }
        return percentSum;
    }

    private double getRawPercent(){
        double grade = 0.0;


        for(String assignment : assignmentList.keySet()){
            double assignmentGrade = Objects.requireNonNull(assignmentList.get(assignment))[0];
            double percent = Objects.requireNonNull(assignmentList.get(assignment))[1];
            grade += percent/100 * assignmentGrade;
        }

        return grade;
    }

    public String getCourseGrade(){
        String ans = "Course grade is: ";

        double grade = 0.0;
        double percentSum = getPercentSum();

        for(String assignment : assignmentList.keySet()){
            double assignmentGrade = Objects.requireNonNull(assignmentList.get(assignment))[0];
            double percent = Objects.requireNonNull(assignmentList.get(assignment))[1];
            grade += percent/percentSum * assignmentGrade;
        }

        ans += grade + "\n";

        return ans;
    }

    public double percentNeeded(double targetGrade){
        String[] currentGrade = getCourseGrade().replaceAll("[^0-9.]+", " ").trim().split(" ");

        try {
            double x = (targetGrade - getRawPercent())/((100-getPercentSum())/100);
            return (targetGrade - getRawPercent())/((100-getPercentSum())/100);
        }catch (Exception e){
            System.out.println("This course is complete\n");
        }
        return 0;
    }



    public boolean addAssignment(String assignmentName, Double grades, Double percent) {
        if(assignmentList.containsKey(assignmentName)){
            return false;
        }

        if (((courseSum + percent) <= 100)) {
            courseSum += percent;
            assignmentList.put(assignmentName, new Double[2]);
            Objects.requireNonNull(assignmentList.get(assignmentName))[0] = grades;
            Objects.requireNonNull(assignmentList.get(assignmentName))[1] = percent;
            return true;
        } else {
            return false;
        }

    }

    public String getCourseName() {
        return courseName;
    }

    public String getYear() {
        return year;
    }

    public String getSeason() {
        return season;
    }
}
