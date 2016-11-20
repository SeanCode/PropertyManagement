package cn.edu.cqupt.wyglzx;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * Created by cc on 16/11/10.
 */
public class Pinyin {
    public static void main(String[] args) throws PinyinException {
        String s = "1/[你好y世界]";
        System.out.println(PinyinHelper.getShortPinyin(s));

        System.out.println("|01|".substring(1, 3));
    }
}
