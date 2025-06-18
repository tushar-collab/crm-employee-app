package com.ps.assignment.employeeManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class Test{

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        // list = list.stream().map(elem -> {elem + elem}).toList();
        for(Integer x : list){
            try{
                if(x == 0){
                    throw new InterviewException("divide by zero");
                }
                System.out.println(1 / 0);
            }catch(InterviewException e){
                System.out.println("Divide bny 0");
            }
        }
        // System.out.println(list);

    }

}