package cn.edu.cqupt.wyglzx.common;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Util {

    public static Long time() {
        return (System.currentTimeMillis() / 1000);
    }

    public static boolean isLatestDays(Long time, int day) {
        Long diff = Math.abs(time() - time);

        return 3600 * 24 * day > diff;
    }

    public static String getTimeString(DateFormat dateFormat, long timeStamp) {
        return dateFormat.format(new Date(timeStamp * 1000));
    }

    public static String getTimeString(long timeStamp) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(timeStamp * 1000));
    }

    public static int parseInt(Object obj) {
        try {
            return (int) Double.parseDouble("" + obj);
        } catch (Exception e) {

        }

        return 0;
    }

    public static long parseLong(Object obj) {
        try {
            return (long) Double.parseDouble("" + obj);
        } catch (Exception e) {

        }
        return 0;
    }

    public static String generateRandomString(int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random r = new Random(System.currentTimeMillis());
        char[] id = new char[length];
        for (int i = 0; i < length; i++) {
            id[i] = chars[r.nextInt(chars.length)];
        }
        return new String(id);
    }

    public static String implodeIdString(List<String> list) {
        if (list == null || list.size() == 0) {
            return "|1|";
        }
        return "|" + Joiner.on("|").skipNulls().join(list) + "|";
    }

    public static String implode(List<String> list, String glue) {
        if (list == null ||list.size() <= 0) {
            return "";
        }
        return Joiner.on(glue).skipNulls().join(list);
    }

    public static Map<String, Integer> convertDecToIntValueByKeys(Long dec, List<String> keys) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int length = keys.size();
        int i = 0;
        for (; i < length; i++) {
            String key = keys.get(i);
            int bitValue = (int) Math.pow(2, i);
            if ((dec & bitValue) == bitValue) {
                map.put(key, 1);
            } else {
                map.put(key, 0);
            }
        }
        return map;
    }

    public static String filterIdString(String idString) {
        HashSet<String> idStringSet = Sets.newHashSet(Splitter.on('|')
                .trimResults()
                .omitEmptyStrings()
                .split(idString));
        return "|" + Joiner.on("|").join(idStringSet) + "|";
    }

    public static String filter(String s, String glue) {
        HashSet<String> set = Sets.newHashSet(Splitter.on(glue)
                .trimResults()
                .omitEmptyStrings()
                .split(s));
        return Joiner.on(glue).skipNulls().join(set);
    }

    public static String stickIdToIdString(String idString, Long id) {
        HashSet<String> idStringSet = Sets.newHashSet(Splitter.on('|')
                .trimResults()
                .omitEmptyStrings()
                .split(idString + id));

        return "|" + Joiner.on("|").join(idStringSet) + "|";
    }

    public static List<String> explodeIdString(String idString) {
        HashSet<String> idStringSet = Sets.newHashSet(Splitter.on('|')
                .trimResults()
                .omitEmptyStrings()
                .split(idString));

        return new ArrayList<>(idStringSet);
    }

    public static List<String> explodeUrlString(String urlString) {
        HashSet<String> idStringSet = Sets.newHashSet(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split(urlString));

        return new ArrayList<>(idStringSet);
    }

//    public static void main(String[] args) {
//        System.out.println(time());
//        String s = stickIdToIdString("|1|2|3|1|", 4L);
//        System.out.println(time());
//        System.out.println(s);
//
//        System.out.println(explodeIdString("|1|2|3|4|").toString());
//
//        List<String> idList = new ArrayList<>();
//        idList.add(1 + "");
//        idList.add(2 + "");
//        idList.add(3 + "");
//
//        System.out.println(implodeIdString(idList));
//    }
//
//    public static void main(String[] args) {
//        System.out.println(Util.explodeUrlString("a.com,b.com,c.com,"));
//    }

}
