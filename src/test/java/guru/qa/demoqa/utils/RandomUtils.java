package guru.qa.demoqa.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    private static final Random random = new Random();

    public static String getRandomString(int length){

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder result = new StringBuilder();
        while (result.length() < length){
            int index = (int) (random.nextFloat() * chars.length());
            result.append(chars.charAt(index));
        }

        return result.toString();
    }

    public static int getRandomInt(int min, int max){
        return random.nextInt( (max - min) + 1) + min;
    }

    public static Long getRandomLong(Long min, Long max){
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static String getRandomPhone(){
        return getRandomLong(1000000L, 9999999L).toString();
    }

    public static String getRandomPhone(String code){
        return code + getRandomPhone();
    }

    public static String getFormattedPhone(String code){
        return String.format(
                "+7(%s)%s",
                code,
                getRandomPhone()
        );
    }

    public static String getRandomEmail(){
        return getRandomString(10).toLowerCase() + "mail.ru";
    }

}
