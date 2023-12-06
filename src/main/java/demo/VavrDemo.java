package demo;

import common.StudentValidator;
import entity.Student;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.collection.TreeSet;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;

public class VavrDemo {


    @Test
    public void tryDemo() {
        //不会抛出异常
        Try.of(() -> 1 / 0);

    }

    @Test
    public void streamDemo() {
        //固定长度(不可新增，不可修改)
        List<String> java = Stream.of(1, 2, 3).append(4).map(Objects::toString).asJava();
//        java.add("-");
//        java.set(3, "-");
        System.out.println(java);
    }


    @Test
    public void treeSetDemo(){
        //倒序
        Comparator<Integer> comparator = (o1, o2) -> o2 - o1;
        TreeSet<Integer> integers = TreeSet.of(comparator);
        for (int i = 0; i < 5; i++) {
            integers = integers.add(i);
        }
        integers.forEach(x -> System.out.println(x));
    }

    @Test
    public void concatDemo(){
        List<String> list = Arrays.asList("a", "b", "c");
        String s1 = io.vavr.collection.List.ofAll(list).mkString(",");
        System.out.println(s1);
        String s = io.vavr.collection.List.of("a", "b", "c").mkString("-");
        System.out.println(s);
    }

    @Test
    public void tupleDemo(){
        Tuple2<String, Integer> java = Tuple.of("java", 8);
        Tuple2<String, Integer> map = java.map(s -> s.substring(2), i -> i / 8);
        System.out.println(map._1);
        System.out.println(map._2);

        //转换
        Integer apply = java.apply((s, i) -> s.length() + i);
        System.out.println(apply);
    }

    @Test
    public void  functionDemo(){
        Function2<String, String, Integer> lengthSum = (s1, s2) -> s1.length() + s2.length();
        Integer apply = lengthSum.apply("ab", "abc");
        System.out.println(apply);

        //方法组合
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> plusTwo = a -> a + 2;
        Integer apply1 = plusOne.andThen(plusTwo).apply(1);
        System.out.println(apply1);

    }

    @Test
    public void validatorDemo(){
        Validation<Seq<String>, Student> a = this.validatePerson("a", 1);
        Validation<Seq<String>, Student> b = this.validatePerson(null, 1);
        Student student = a.get();
        System.out.println(student);

        if(b.isInvalid()){
            b.getError().forEach(x -> System.out.println(x));
        }

    }


    public Validation<Seq<String>, Student> validatePerson(String name, Integer age) {
        return Validation.combine(validateName(name), validateAge(age)).ap(Student::new);
    }

    private Validation<String, Integer> validateAge(Integer age) {
        return age == null ? Validation.invalid("age is null")  : Validation.valid(age);
    }

    private Validation<String, String> validateName(String name) {
        return name == null ? Validation.invalid("name is null")  : Validation.valid(name);
    }

}
