package psjw.webflux.basic.ch04;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class MethodReferenceExample {
    public static void main(String[] args) {
        var target = new Person("f");
        Consumer<String> staticPrint = MethodReferenceExample::print;

        Stream.of("a","b","g","h")
                .map(Person::new) //constructor reference
                .filter(target::compareTo)//method reference
                .map(Person::getName) //instance method reference
                .forEach(staticPrint); //static method reference
    }

    @RequiredArgsConstructor
    public static class Person{
        @Getter
        private final String name;

        public Boolean compareTo(Person o){
            return o.name.compareTo(name) > 0;
        }
    }

    public static void print(String name){
        System.out.println(name);
    }

}

