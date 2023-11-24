package demo;

import org.junit.Test;
import service.PrintTime;
import service.Printable;
import service.impl.Cat;

public class Test1 {


    @Test
    public void print() {
        System.out.println("hello world");
    }


    @Test
    public void lambdas() {
        Cat cat = new Cat();
        printThing(cat);
        //////////////////////////////////////
        printThing(() -> System.out.println("Meow1"));
        /////////////////////////
        Printable printable = () -> System.out.println("Meow2");
        printThing(printable);
    }

    private void printThing(Printable thing) {
        thing.print();
    }

    @Test
    public void slowNum(){
        printTimeElapsed(() -> {
            long sum = 0l;
            for (int i = 0; i < 100000; i++) {
                sum += i;
                System.out.println(sum);
            }
        });
    }

    private void printTimeElapsed(PrintTime thing) {
        long s = System.currentTimeMillis();
        thing.process();
        long e = System.currentTimeMillis();
        System.out.println(e - s);

    }

    //测试提交
}
