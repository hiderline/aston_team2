/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gradleproject1;

/**
 *
 * @author User
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBusFillStrategy implements BusFillStrategy {

    @Override
    public List<Bus> fillBuses(int size) {
        List<Bus> buses = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\buses.txt"));
            String line = reader.readLine();

            for (int i=0;i<size;i++) {
                System.out.println(line);
                line = reader.readLine();


                String[] split = line.split(" ");

                Bus bus;
                bus = new Bus.Builder()
                        .setNumber(Integer.parseInt(split[0]))
                        .setModel(split[1])
                        .setMileage(Integer.parseInt(split[2]))
                        .build();

                buses.add(bus);

            }
            reader.close();

        }catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        }catch (NullPointerException e){
            System.out.println("Out of file memory");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Incorrect file format");
        }catch (FileNotFoundException e){
            System.out.println("Incorrect file path");
        } catch (IOException ex) {
            System.getLogger(FileBusFillStrategy.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }





        return buses;
    }
}
