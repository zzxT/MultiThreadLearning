package mylock.loopprint_abc;

import sun.java2d.pipe.RenderingEngine;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {


    public static class PrintThread extends Thread {

        private ReentrantLock reentrantLock;
        private String msg;
        private Condition next;
        private Condition current;
        public PrintThread(ReentrantLock reentrantLock, String msg, Condition current,  Condition next) {
            this.reentrantLock = reentrantLock;
            this.msg = msg;
            this.next = next;
            this.current = current;
        }


        @Override
        public void run() {
            reentrantLock.lock();
            for (int i = 0; i < 3; i++) {

                System.out.println(msg);

                try {
                    next.signal();
                    current.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            next.signal();
            reentrantLock.unlock();
            System.out.println("unlock " + msg);
        }
    }

    public static void main(String[] args) {

//        TicketLock ticketLock;
//        PrintThread printThreadA = new PrintThread(null, null);
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionA = reentrantLock.newCondition();
        Condition conditionB = reentrantLock.newCondition();
        Condition conditionC = reentrantLock.newCondition();

        PrintThread printThreadA = new PrintThread(reentrantLock, "A", conditionA,  conditionB);
        PrintThread printThreadB = new PrintThread(reentrantLock, "B", conditionB,  conditionC);
        PrintThread printThreadC = new PrintThread(reentrantLock, "C", conditionC, conditionA);

        printThreadA.start();
        printThreadB.start();
        printThreadC.start();
//        System.out.println("hello");
    }

}
