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
        //��ѭ��ִ�д�ӡ"I am running!" ��������ʱ��ĸ������
        while (true) {
            System.out.println("I am running!");
            
            for (int i = 0; i < 900000; i++) {
                d =  d + (Math.PI + Math.E) / d;
            }
            //���̵߳����������л����������̵��ź�
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
        //�����񽻸�һ���߳�ִ��
        Thread t = new Thread(new ATask());
        t.start();
        
        //����һ��ʱ���ж��߳�
        Thread.sleep(5000);
        System.out.println("****************************");
        System.out.println("Interrupted Thread!");
        System.out.println("****************************");
        t.interrupt();
    }
} 
