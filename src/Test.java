import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

class ATask implements Runnable{

    private double d = 0.0;
    
    public void run() {
        //死循环执行打印"I am running!" 和做消耗时间的浮点计算
        while (true) {
            System.out.println("I am running!");
            
            for (int i = 0; i < 900000; i++) {
                d =  d + (Math.PI + Math.E) / d;
            }
            //给线程调度器可以切换到其它进程的信号
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

public class Test {
    
    public static void main(String[] args) throws Exception{
        //将任务交给一个线程执行
        Thread t = new Thread(new ATask());
        t.start();
        
        //运行一断时间中断线程
        Thread.sleep(5000);
        System.out.println("****************************");
        System.out.println("Interrupted Thread!");
        System.out.println("****************************");
        t.interrupt();
    }
} 
