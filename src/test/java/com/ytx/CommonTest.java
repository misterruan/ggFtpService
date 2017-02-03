package com.ytx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by rock on 2016/12/29.
 */
public class CommonTest
{

    @Test
    public void testListClear(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i <20 ; i++) {
            list.add(i);
        }
        /**
         * 此时返回的integersList并不是一个新对象，而是list的0-9位的映射,
         * 如果修改integersList的值就等价于修改list的值，所以integersList.clear()
         * 清空integersList之后，list的0-9位也清空了，长度只剩下10位了。
         */
        List<Integer> integersList = list.subList(0, 10);
        integersList.clear();
    }


    @Test
    public void test2(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
    //星期六或星期天。星期天是1，类推
        if(day==1||day==7){
            //你的代码
            int day2 = calendar.get(Calendar.DAY_OF_WEEK);
        }else{
            //你的代码
        }
    }





}
