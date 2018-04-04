package com.example.virtualstore.domain.valueObjects.payment;

import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BarCode {

    private List<String> code;

    public BarCode(double total) {
        code = new LinkedList<>();
        code.add(getRandomXDig(5) + "." + getRandomXDig(5));
        code.add(getRandomXDig(5) + "." + getRandomXDig(5));
        code.add(getRandomXDig(5) + "." + getRandomXDig(5));
        code.add(getRandomXDig(1));
        code.add(getRandom14Dig(total));
    }

    private String getRandomXDig(int xDig) {
        int min = (int) Math.pow(10, xDig-1);
        int max = (int) Math.pow(10, xDig);
        return String.valueOf(ThreadLocalRandom.current().nextLong(min, max));
    }

    private String getRandom14Dig(double total) {
        BigInteger bigInteger = BigInteger.valueOf(ThreadLocalRandom.current().nextInt(1000, 10000));
        bigInteger = bigInteger.multiply(new BigInteger("10000000000"));
        bigInteger = bigInteger.add(new BigInteger(String.valueOf((int)(total*100))));
        return String.valueOf(bigInteger);
    }

    public List<String> getCode() {
        return Collections.unmodifiableList(code);
    }

    public void setCode(List<String> list) {
        code = list;
    }

    public double getTotal() {
        String lastString = code.get(code.size()-1);
        String totalString = lastString.substring(4);
        return Double.valueOf(totalString);
    }

}
