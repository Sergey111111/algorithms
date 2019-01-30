package com.site.initorder;

public class Test {
    private static class ClassA {
        int i = 1;

        {
            System.out.println(getI());
            System.out.println(i);
        }

        ClassA() {
            i = 2;
            System.out.println(getI());
            System.out.println(i);
        }

        int getI() {
            return i;
        }
    }

    private static class ClassB extends ClassA {
        int i = 3;

        {
            i = 4;
        }

        int getI() {
            return i;
        }

        ClassB() {
            System.out.println(getI());
            System.out.println(i);
        }
    }

    private static class ClassC extends ClassB {
        int i = 5;

        {
            i = 6;
        }

        int getI() {
            return i;
        }

        ClassC() {
            System.out.println(getI());
            System.out.println(i);
        }
    }

    public static void main(String... args) {
        ClassC c = new ClassC();
        System.out.println(c.getI());
    }

}