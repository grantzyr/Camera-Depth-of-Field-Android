package com.example.Model;

public class StaticTest {
    public static int staticCount = 0;
    public int instanceCount = 0;

    public static void main(String[] args) {
        System.out.println(StaticTest.staticCount);

        StaticTest t1 = new StaticTest();
        t1.staticCount = 100;
        t1.instanceCount = 200;
        System.out.println(staticCount);

        StaticTest t2 = new StaticTest();
        System.out.println(t2.staticCount);
        System.out.println(t2.instanceCount);
    }

    public static void myStaaticMethod() {
        System.out.println("Static Test Static Method Executed");
    }

    public void myMethod() {
        System.out.println("Static Test non Static Method Executed");
    }
}
