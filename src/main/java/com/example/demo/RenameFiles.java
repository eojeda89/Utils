package com.example.demo;

import java.io.File;

public class RenameFiles {

    public static void main(String[] args) {
        String directoryPath = "J:\\6-Estudio\\Udemy - Spring Boot Microservices with Spring Cloud Beginner to Guru 2021-11\\21 - Service Discovery with Eureka\\";
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            for (File file : files) {
                if(file.getName().endsWith(".srt")) {
                    String fileName = file.getName();
                    String newFileName = fileName.replace("_Downloadly.ir_en", "");
                    File newFile = new File(directoryPath + "\\" + newFileName);
                    file.renameTo(newFile);
                }
            }
            System.out.println("Files renamed successfully.");
        } else {
            System.out.println("Directory not found.");
        }
    }

}
