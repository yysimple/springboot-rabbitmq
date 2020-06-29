package com.jxkj.test;

public class StringTest {
    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        String ab1 = new String("ab");
        String ab2 = "ab";
        String ab3 = "a" + "b";
        String ab4 = a + b;
        String ab5 = "a" + b;
        String ab6 = a + "b";
        String ab7 = ab4.intern();
        String ab8 = ab5.intern();


        System.out.println("ab1 == ab2: " + (ab1 == ab2));
        System.out.println("ab2 == ab3: " + (ab2 == ab3));
        System.out.println("ab4 == ab5: " + (ab4 == ab5));
        System.out.println("ab2 == ab5: " + (ab2 == ab5));
        System.out.println("ab2 == ab4: " + (ab2 == ab4));
        System.out.println("ab3 == ab4: " + (ab3 == ab4));
        System.out.println("ab5 == ab6: " + (ab5 == ab6));
        System.out.println("ab2 == ab7: " + (ab2 == ab7));
        System.out.println("ab2 == ab8: " + (ab2 == ab8));



        String s = new String("1");
        String s1 = "1";
        s1.intern();

        System.out.println("s == s1: " + (s == s1));

        String s2 = new String("11") + new String("11");
        s2.intern();
        String s3 = "1111";
        System.out.println("s2 == s3: " + s2 == s3);

    }
}
