package com.preschool.demo.mulakat;

import java.util.Arrays;
import java.util.List;

public class Test3
{

    public static void main(String[] args)
    {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

        int sum = list.stream()
                .filter(r -> r % 2 == 0)
                .mapToInt(r -> r * r)
                .sum();


        int a = 5;
        int b = 10;
        System.out.println(a++ + ++b);
        System.out.println(a++);
        System.out.println(a);

    }

}
