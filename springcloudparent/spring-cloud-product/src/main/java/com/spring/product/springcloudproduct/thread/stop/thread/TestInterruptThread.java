package com.spring.product.springcloudproduct.thread.stop.thread;

public class StopThreadUnsafe {

    public static User u = new User();

    public static class ChangeObjectThread extends Thread{

        @Override
        public void run() {
           while(true){
               synchronized (u){
//                   System.out.println("ChangeObjectThread:start");
                   int v = (int)System.currentTimeMillis()/1000;
                   u.setId(v);
                   try {
                       Thread.sleep(100);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   u.setName(v+"");
//                   System.out.println("ChangeObjectThread:end");
               }
               Thread.yield();
           }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while(true){
                synchronized (u){
                    System.out.println("ReadObjectThread:start");
                    if(u.getId() != Integer.parseInt(u.getName())){
                        System.out.println("read:"+u.toString());
                    }
                    System.out.println("ReadObjectThread:end");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while(true){
            Thread t = new ChangeObjectThread();
            t.start();
             Thread.sleep(150);
            t.stop();
         }
    }

}
