package demo;

import java.util.Arrays;
import java.util.List;

import static com.sun.deploy.uitoolkit.impl.awt.AWTClientPrintHelper.print;

public class Test2 {

    public static void main(String[] args) {
        String aaa = print("aaa", 123);
        ////////////////////////
        List<Integer> integerList = Arrays.asList(123);
        printList(integerList);
        List<String> stringList = Arrays.asList("aaa");
        printList(stringList);

    }


    private static <T, V> T print(T thing, V otherThing) {
        System.out.println(thing);
        System.out.println(otherThing);
        return thing;
    }

    private static void printList(List<?> list) {
        System.out.println(list);
    }
}
