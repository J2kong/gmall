package com.dmeo.gmall.cart.controller;

import java.math.BigDecimal;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/3 19:56
 **/
public class TestBd {

    public static void main(String[] args) {
        BigDecimal b = new BigDecimal(0.01f);
        BigDecimal b2 = new BigDecimal(0.01d);
        BigDecimal b3 = new BigDecimal("0.01");
        BigDecimal b4 = new BigDecimal("6");
        BigDecimal b5 = new BigDecimal("7");

        System.out.println(b);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b.add(b2));
        System.out.println(b3.add(b3));
        System.out.println(b2.multiply(b));
        System.out.println(b2.subtract(b));

        System.out.println(b4.divide(b5,5,BigDecimal.ROUND_HALF_DOWN));
    }
}
