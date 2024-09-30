package com.preschool.demo.mulakat;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// MyClass sınıfı dışına taşındı, böylece tüm kod blokları tarafından erişilebilir hale geldi.
@Getter
class Person implements Comparable<Person>
{
    // Getter metodları eklendi
    private String name;
    private int age;

    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    // toString metodu override edildi
    @Override
    public String toString()
    {
        return "Person{name='" + name + "', age=" + age + "}";
    }

    // compareTo metodu override edilerek sıralama işlemi sağlandı
    @Override
    public int compareTo(Person other)
    {
        return Integer.compare(this.age, other.age);
    }
}

public class Test2
{
    public static void main(String[] args)
    {
        List<Person> list = new ArrayList<>();
        list.add(new Person("A", 12));
        list.add(new Person("B", 15));
        list.add(new Person("C", 13));

        // Collections.sort(list) ile list.sort() arasında fark yoktur; her ikisi de çalışır
        Collections.sort(list);

        for (Person person : list)
        {
            System.out.println(person.toString());
        }
    }
}
