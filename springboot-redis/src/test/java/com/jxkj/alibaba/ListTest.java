package com.jxkj.alibaba;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListTest {

    @Test
    public void toArrayTest() {
        List<String> list = new ArrayList<>(2);
        list.add("gujin");
        list.add("mayun");
        /*String[] array = new String[list.size()];
        array = list.toArray(array);*/

        // 这种情况只能进行强转
        Object[] array = list.toArray();
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void foreachListAddTest() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        /**
         * 以下方式会报异常
         * 当你在foreach 它的时候，并对其进行修改，就会出现这种异常
         * java.util.ConcurrentModificationException：
         * 当不允许这样的修改时，可以通过检测到对象的并发修改的方法来抛出此异常。
         */
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
                System.out.println(item + " ----> " + list);
            }
        }

        /*Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("1".equals(item)) {
                list.remove(item);
                System.out.println(item + "--->" + list);
            }
        }*/
    }



}
