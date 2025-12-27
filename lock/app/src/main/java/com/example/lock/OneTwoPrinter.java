/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lock;

/**
 *
 * @author User
 */
public class OneTwoPrinter {
 
    private static final Object lock = new Object();
    private static boolean printOne = true;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!printOne) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("1"); // ← println
                    printOne = false;
                    lock.notifyAll();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (printOne) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("2"); // ← println
                    printOne = true;
                    lock.notifyAll();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
