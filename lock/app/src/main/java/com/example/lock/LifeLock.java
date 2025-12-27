/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lock;

/**
 *
 * @author User
 */
public class LifeLock {
    static class Worker {
        private final String name;
        private boolean shouldWork = true;
        Worker(String name) {
            this.name = name;
        }
        void work(Worker other) {
            while (shouldWork) {
                if (other.shouldWork) {
                    System.out.println(name + " уступает " + other.name);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(name + " работает");
                    shouldWork = false;
                }
            }
        }
    }
    public static void main(String[] args) {
        Worker w1 = new Worker("Worker-1");
        Worker w2 = new Worker("Worker-2");
        new Thread(() -> w1.work(w2)).start();
        new Thread(() -> w2.work(w1)).start();
    }
}
