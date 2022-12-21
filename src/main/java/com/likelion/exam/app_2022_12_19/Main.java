package com.likelion.exam.app_2022_12_19;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
@Getter
class Person {
    int id;
    int age;
    String name;
}

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        people.add(new Person(2, 26, "장서현"));
        people.add(new Person(1, 26, "장서현"));
        people.add(new Person(2355467, 26, "장서현"));

        int foundIndex = -1;

        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).id == 1) {
                foundIndex = i;
                break;
            }
        }

        System.out.printf("foundIndex: %d\n", foundIndex);

        IntStream.range(0, people.size())
                .filter(i -> people.get(i).getId() == 1)
                .forEach(i -> System.out.println(people.get(i).getAge()));
    }
}