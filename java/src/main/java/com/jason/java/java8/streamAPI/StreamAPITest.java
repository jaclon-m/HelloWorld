package com.jason.java.java8.streamAPI;


import com.jason.java.java8.Employee;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPITest {

    public static void main(String[] args) throws ParseException {
        testX();
    }

    //创建Stream
    public void test1(){

        //1 steam()和parallelSteam()方法
        ArrayList<String> list  = new ArrayList<>();
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();

        //2 Arrays steam()方法
        Integer[] num = new Integer[10];
        Stream<Integer> integerStream = Arrays.stream(num);

        //3 通过Stream中的静态方法of()
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);

        //4 创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2).limit(10);
        stream3.forEach(System.out::println);

        //生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(5);
        stream4.forEach(x-> System.out.print(x + " "));
    }

    //中间操作

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    //筛选与切片
    public void test2(){

        Stream<Employee> stream = emps.stream().filter(e->{
            System.out.println("测试中间操作");
            return e.getAge()>35;});

        stream.forEach(System.out::println);
    }

    public void test3(){

        /*emps.stream().filter(e->{
            System.out.println("短路");
            return e.getSalary()>=5000;
        }).limit(3).forEach(System.out::println);*/
        //emps.stream().filter(e->e.getSalary()>=5000).skip(2).forEach(System.out::println);
        emps.stream().filter(e->e.getSalary()>=5000).distinct().forEach(System.out::println);

    }

    public static void testX() throws ParseException {
        String str = "2021-07-12 00:00:00";
        Date date1 = DateUtils.parseDate(str, "yyyy-MM-dd HH:mm:ss");
        String str2 = "2021-08-05 21:02:10";
        Date date2 = DateUtils.parseDate(str2, "yyyy-MM-dd HH:mm:ss");
        ArrayList<Date> list = new ArrayList<>();
        list.add(date1);
        list.add(date2);
        System.out.println("==============" + date1.after(new Date()));
        System.out.println(date2 + "==============" + date2.before(new Date()));
        System.out.println(date2 + "==============" + date2.after(new Date()));
        list.stream().filter(x->x.after(new Date())).forEach(x -> System.out.println(x.toString()));
    }
    //映射
    public  void test4(){
        /*map——接收 Lambda ， 将元素转换成其他形式或提取信息。
          接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
		  flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流*/
        emps.stream().map(e->e.getName()).forEach(System.out::println);
        List<String> str = Arrays.asList("aaa", "bbb", "ccc", "def");
        Stream<Character> stream = str.stream().flatMap(StreamAPITest::filterCharacter);
        stream.forEach(System.out::println);
    }

    public static Stream<Character>filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for(Character c:str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    //排序
    public void test5(){
        //emps.stream().map(e->e.getName()).sorted().forEach(System.out::println);
        emps.stream().sorted((x,y)->{
            if(x.getAge().equals(y.getAge())){
                return x.getName().compareTo(y.getName());
            }else {
                return x.getAge().compareTo(y.getAge());
            }
        }).forEach(System.out::println);

    }

    //终止操作

    //规约
    public void test6(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        Optional<Double> op = emps.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(op.get());
    }

    //搜索名字中出现6的次数
    public void test7(){
        /*long count = emps.stream().filter(x -> x.getName().contains("六")).count();
        System.out.println(count);*/

        Optional <Integer> num = emps.stream().map(Employee::getName).flatMap(StreamAPITest::filterCharacter).map(ch -> {
            if (ch.equals('六')) {
                return 1;
            } else {
                return 0;
            }
        }).reduce(Integer::sum);
        System.out.println(num.get());
    }

    //收集

    public  void test8(){
        Map <Double, List <Employee>> listMap = emps.stream().collect(Collectors.groupingBy(Employee::getSalary));
        System.out.println(listMap);
    }
}
