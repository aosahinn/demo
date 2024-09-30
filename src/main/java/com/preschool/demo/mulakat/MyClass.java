package com.preschool.demo.mulakat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyClass
{
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Person implements Comparable<Person>
    {
        private String name;
        private int age;

        @Override
        public String toString()
        {
            return "Person{name='" + name + "', age=" + age + "}";
        }

        @Override
        public int compareTo(Person other)
        {
            return Integer.compare(this.age, other.age);
        }
    }

    public static void main(String[] args)
    {
        List<Person> list = new ArrayList<>();
        list.add(new Person("A", 12));
        list.add(new Person("B", 15));
        list.add(new Person("C", 13));

        Collections.sort(list);

        for (Person person : list)
        {
            System.out.println(person.toString());
        }
    }
}
