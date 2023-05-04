package util;

import com.google.gson.Gson;
import entity.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {


    @Test
    public void json2Object(){
        String json = "{name='张三', age=17}";
        Gson gson = new Gson();
        Student student = gson.fromJson(json, Student.class);
        System.out.println(student);
    }

    @Test
    public void json2List(){
        String json = "[{name='张三', age=17}, {name='李四', age=18}]";

        Gson gson = new Gson();
        Student[] student = gson.fromJson(json, Student[].class);
        List<Student> list = Arrays.asList(student);
        System.out.println(list);
    }

    @Test
    public void map2Json(){
        Map<String, Integer> map = new HashMap<>(2);
        map.put("张三", 17);
        map.put("李四", 18);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        System.out.println(json);
    }

}
