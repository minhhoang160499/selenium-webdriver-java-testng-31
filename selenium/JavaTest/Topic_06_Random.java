package JavaTest;

import java.util.Random;

public class Topic_06_Random {
    // Java Builtin (Cung cấp sẵn - lấy ra sử dụng)
    // Java Libraries (Do 1 cá nhân/ tổ chức họ tự viết)

    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt());
        System.out.println(rand.nextInt());

        System.out.println(rand.nextInt());
        System.out.println(rand.nextFloat());
        System.out.println(rand.nextBoolean());

        System.out.println("automation" + rand.nextInt(99999) + "@gmail.net");
        System.out.println("automation" + rand.nextInt(99999) + "@gmail.net");
        System.out.println("automation" + rand.nextInt(99999) + "@gmail.net");
        System.out.println("automation" + rand.nextInt(99999) + "@gmail.net");

        System.out.println("automation" + Math.round(Math.random() * 1000000) + "@gmail.net");
        System.out.println("automation" + Math.round(Math.random() * 1000000) + "@gmail.net");
        System.out.println("automation" + Math.round(Math.random() * 1000000) + "@gmail.net");
    }
}
