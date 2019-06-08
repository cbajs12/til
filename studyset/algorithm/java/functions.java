import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class functions {
    public static void recursionMain(String[] args){
        System.out.println("Hello World!"+args[0]);
        String numberString = args[0];
        int number = Integer.parseInt(numberString);
        int result = functions.calculator(number);
        System.out.println(result);
        //////////////////////////////////////////
        int number2 = Integer.parseInt(args[0]);
        List<Integer> binary = new ArrayList<>();
        List<Integer> result2 = functions.convertBinary(number,binary);
        for(int i = binary.size() - 1  ; i >= 0 ; --i ){
            System.out.println(binary.get(i));
        }
        /////////////////////////////////////////
        AAA ref1 = new CCC();
        BBB ref2 = new CCC();
        CCC ref3 = new CCC();

        ref1.ca();
        System.out.println(ref1.ma);
        ref2.ca();
        System.out.println(ref2.ma);
        ref3.ca();
        System.out.println(ref3.ma);
        /////////////////////////////////////////

    }

    public static int calculator(int count){
        if(count==1){
            return 2;
        }else{
            return 2*calculator(--count);
        }
    }

    public static List<Integer> convertBinary(int number, List<Integer> binary){
        if(number==1){
            binary.add(number);
            return binary;
        }else{
            binary.add(number%2);
            return convertBinary(number/2, binary);
        }
    }

    public static int toBinary(int decimal){        // example
        if(decimal > 0) {
            int bin;
            bin = decimal % 2;
            decimal /= 2;
            toBinary(decimal);
            System.out.println(bin);
        }
        return 0;
    }

    public int binarysort(int decimal){
        return 2;
    }

    public int binarysort(int decimal, int local){
        String name = "this"+3;
        return 3;
    }
}

class AAA {
    char ma = 'a';

    public void ca(){
        System.out.println("ca");
    }
}

class BBB extends AAA{
    char ma = 'b';

    public void ca(){
        System.out.println("ca2");
    }
}

class CCC extends BBB{
    char ma = 'c';

    public void ca(){
        System.out.println("ca3");
    }
}

class Sum{
    int num;
    public Sum(){ num=0; }
    public void addNum(int n){ num+=n; }
    public int getNum(){ return num; }
}

class AdderThread extends Sum implements Runnable{
    int start, end;

    public AdderThread(int s, int e){
        start = s;
        end = e;
    }
    @Override
    public void run() {
        for (int i= start; i<=end; i++)
            addNum(i);
    }

    public void threadMain(){
        AdderThread at1 = new AdderThread(1, 50);
        AdderThread at2 = new AdderThread(51, 100);
        Thread tr1 = new Thread(at1);
        Thread tr2 = new Thread(at2);
        tr1.start();
        tr2.start();

        try{
            tr1.join();
            tr2.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Total sum : " + (at1.getNum()+at2.getNum()));
    }
}

class AdderThreadChange extends Thread{
    int n;
    int start, end;

    public AdderThreadChange(int s, int e){
        start = s;
        end = e;
        n = 0;
    }
    @Override
    public void run() {
        for (int i= start; i<=end; i++)
            addNum(i);
    }
    public void addNum(int num){n+=num;}
    public int getNum(){return n;}
}

class Calculator{
    int opCnt=0;

    public synchronized int add(int n1, int n2){
        opCnt++;
        return n1+n2;
    }

    public synchronized int min(int n1, int n2){
        opCnt++;
        return n1-n2;
    }
    public void showOpCnt(){
        System.out.println(opCnt);
    }
}

class AddCal extends Thread{
    Calculator cal;

    public AddCal(Calculator cal){this.cal = cal;}

    public void run(){
        System.out.println("1+2="+cal.add(1,2));
    }

}

class MinCal extends Thread{
    Calculator cal;

    public MinCal(Calculator cal){this.cal = cal;}

    public void run(){
        System.out.println("2-1="+cal.min(2,1));
    }

}

class TwoNum{
    int num1=0;
    int num2=0;

    public void addOneNum1(){
//        synchronized (this){
//            num1+=1;
//        }
        key1.lock();
        try{
            num1+=1;
        }finally {
            key1.unlock();
        }
    }
    public void addOneNum2(){
//        synchronized (this){
//            num1+=2;
//        }
        key1.lock();
        try{
            num1+=2;
        }finally {
            key1.unlock();
        }
    }

    public void addTwoNum1(){
//        synchronized (key2){
//            num2+=1;
//        }
        key2.lock();
        try{
            num2+=1;
        }finally {
            key2.unlock();
        }
    }
    public void addTwoNum2(){
//        synchronized (key2){
//            num2+=2;
//        }
        key2.lock();
        try{
            num2+=2;
        }finally {
            key2.unlock();
        }
    }

    public void showNums(){
        System.out.println("num1 : "+num1);
        System.out.println("num2 : "+num2);
    }

    //Object key1 = new Object();
//    Object key2 = new Object();

    private final ReentrantLock key1 = new ReentrantLock();
    private final ReentrantLock key2 = new ReentrantLock();
}

class AccessThread extends Thread{
    TwoNum twoNum;
    public AccessThread(TwoNum param){
        this.twoNum = param;
    }

    public void run(){
        twoNum.addOneNum1();
        twoNum.addOneNum2();
        twoNum.addTwoNum1();
        twoNum.addTwoNum2();
    }

    public void threadMain(){
        TwoNum twoNum = new TwoNum();
        AccessThread a1 = new AccessThread(twoNum);
        AccessThread a2 = new AccessThread(twoNum);

        a1.start();
        a2.start();
        try{
            a1.join();
            a2.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        twoNum.showNums();
    }
}

class NewsPaper{
    String news;
    boolean isTodayNews=false;

    public void setTodayNews(String news){
        this.news = news;
        isTodayNews=true;

        synchronized (this){
            notifyAll();
        }
    }

    public String getTodayNews(){
        if(isTodayNews==false){
            try {
                synchronized (this){
                    wait();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return news;
    }
}

class Writer extends Thread{
    NewsPaper paper;

    public Writer(NewsPaper paper){
        this.paper = paper;
    }
    public void run(){
        paper.setTodayNews("good news");
    }
}

class Reader extends Thread{
    NewsPaper paper;

    public Reader(NewsPaper paper){
        this.paper = paper;
    }
    public void run(){
        System.out.println("Today news : "+paper.getTodayNews());
    }

    public void threadMain(){
        NewsPaper paper = new NewsPaper();
        Reader a1 = new Reader(paper);
        Reader a2 = new Reader(paper);
        Writer a3 = new Writer(paper);

        try{
            a1.start();
            a2.start();

            Thread.sleep(1000);
            a3.start();

            a1.join();
            a2.join();
            a3.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class StringComm{
    String newString;
    boolean isNewString=false;

    private final ReentrantLock key = new ReentrantLock();
    private final Condition readCond = key.newCondition();
    private final Condition writeCond = key.newCondition();

    public void setNewString(String news){
        key.lock();
        try{
            if(isNewString==true)
                writeCond.await();

            newString=news;
            isNewString=true;
            readCond.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            key.unlock();
        }
    }

    public String getNewString(){
        String retString = null;
        key.lock();
        try {
            if(isNewString==false)
                readCond.await();

            retString = newString;
            isNewString=false;
            writeCond.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            key.unlock();
        }

        return retString;
    }
}

class StringReader extends Thread{
    StringComm comm;

    public StringReader(StringComm comm){
        this.comm = comm;
    }
    public void run(){
        Scanner keyboard=new Scanner(System.in);
        String readStr;

        for(int i =0; i<5; i++){
            readStr=keyboard.nextLine();
            comm.setNewString(readStr);
        }
    }
}

class StringWriter extends Thread{
    StringComm comm;

    public StringWriter(StringComm comm){
        this.comm = comm;
    }
    public void run(){
        for(int i =0; i<5; i++){
            System.out.println("read string: "+comm.getNewString());
        }
    }

    public void threadMain(){
        StringComm stringComm = new StringComm();
        StringReader sr = new StringReader(stringComm);
        StringWriter sw = new StringWriter(stringComm);

        sr.start();
        sw.start();
    }
}

class BufferFileCopy{
    public void copyMain() throws IOException {
        InputStream in = new FileInputStream("a.exe");
        OutputStream out = new FileOutputStream("b.exe");

        int copyByte=0;
        int readLen;
        byte buf[]=new byte[1024];

        while(true){
            readLen = in.read(buf);
            if(readLen == -1)
                break;
            out.write(buf, 0, readLen);
            copyByte += readLen;
        }

        in.close();
        out.close();
        System.out.println(copyByte);
    }

    public void filterMain() throws IOException {
        OutputStream out = new FileOutputStream("b.exe");
        BufferedOutputStream bufOut = new BufferedOutputStream(out);
        DataOutputStream filterOut = new DataOutputStream(bufOut);
        filterOut.writeInt(275);
        filterOut.writeDouble(45.99);
        filterOut.close();

        InputStream in = new FileInputStream("a.exe");
        BufferedInputStream bufIn = new BufferedInputStream(in);
        DataInputStream filterIn = new DataInputStream(bufIn);
        int a = filterIn.readInt();
        double b= filterIn.readDouble();
        filterIn.close();

        System.out.println(a);
        System.out.println(b);
    }

}

class Circle implements Serializable {
    Point p;
    double rad;

    public Circle(int x, int y, double r) {
        p = new Point(x, y);
        rad = r;
    }

    public void showCircleInfo() {
        System.out.printf("[%d, %d] \n", p.x, p.y);
        System.out.println("rad : " + rad);
    }

    public void serialMain() throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Object.ser"));
        out.writeObject(new Circle(1, 1, 2.4));
        out.writeObject(new Circle(2, 2, 4.8));

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Object.ser"));
        Circle cl1 = (Circle)in.readObject();
        Circle cl2 = (Circle)in.readObject();

        cl1.showCircleInfo();
        cl2.showCircleInfo();
    }
}

class Point implements Serializable{
    int x, y;
    public Point(int x, int y){
        this.x= x;
        this.y= y;
    }
}