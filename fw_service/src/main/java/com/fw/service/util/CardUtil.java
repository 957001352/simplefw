package com.fw.service.util;

import com.fw.utils.CheckUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class CardUtil {
    public final Map<String,String> fields = new HashMap<String,String>();

    public CardUtil() {
        fields.put("shotOneLocal","tmInjPosi1");
        fields.put("shotTwoLocal","tmInjPosi2");
        fields.put("shotThreeLocal","tmInjPosi3");
        fields.put("shotFourLocal","tmInjPosi4");
        fields.put("shotFiveLocal","tmInjPosi5");
        fields.put("shotOneStress","tmInjPress1");
        fields.put("shotTwoStress","tmInjPress2");
        fields.put("shotThreeStress","tmInjPress3");
        fields.put("shotFourStress","tmInjPress4");
        fields.put("shotFiveStress","tmInjPress5");
        fields.put("shotOneSpeed","tmInjSpeed1");
        fields.put("shotTwoSpeed","tmInjSpeed2");
        fields.put("shotThreeSpeed","tmInjSpeed3");
        fields.put("shotFourSpeed","tmInjSpeed4");
        fields.put("shotFiveSpeed","tmInjSpeed5");

        fields.put("holdStress","tmInj2HoldPres");
        fields.put("holdLocal","tmInj2HoldPosn");
        fields.put("holdTime","tmInj2HoldTime");
        fields.put("holdOneStress","tmHoldPress1");
        fields.put("holdTwoStress","tmHoldPress2");
        fields.put("holdThreeStress","tmHoldPress3");
        fields.put("holdFourStress","tmHoldPress4");
        fields.put("holdFiveStress","tmHoldPress5");
        fields.put("holdOneSpeed","tmHoldSpeed1");
        fields.put("holdTwoSpeed","tmHoldSpeed2");
        fields.put("holdThreeSpeed","tmHoldSpeed3");
        fields.put("holdFourSpeed","tmHoldSpeed4");
        fields.put("holdFiveSpeed","tmHoldSpeed5");
        fields.put("holdOneTime","tmHoldTime1");
        fields.put("holdTwoTime","tmHoldTime2");

        fields.put("holdThreeTime","tmHoldTime3");
        fields.put("holdFourTime","tmHoldTime4");
        fields.put("holdFiveTime","tmHoldTime5");
        fields.put("storeOneLocal","tmChargePosi1");

        fields.put("storeTwoLocal","tmChargePosi2");
        fields.put("storeThreeLocal","tmChargePosi3");
        fields.put("storeFourLocal","tmChargePosi4");
        fields.put("storeOneStress","tmChargePress1");
        fields.put("storeTwoStress","tmChargePress2");
        fields.put("storeThreeStress","tmChargePress3");
        fields.put("storeFourStress","tmChargePress4");
        fields.put("storeFiveStress","tmChargePress5");
        fields.put("storeOneSpeed","tmChargeSpeed1");
        fields.put("storeTwoSpeed","tmChargeSpeed2");
        fields.put("storeThreeSpeed","tmChargeSpeed3");
        fields.put("storeFourSpeed","tmChargeSpeed4");
        fields.put("storeFiveSpeed","tmChargeSpeed5");
        fields.put("storeOnePress","tmChargeBackPress1");
        fields.put("storeTwoPress","tmChargeBackPress2");
        fields.put("storeThreePress","tmChargeBackPress3");
        fields.put("storeFourPress","tmChargeBackPress4");
        fields.put("shootModel","");
        fields.put("shootDistance","");
        fields.put("coolTime","tmCoolingTime");
        fields.put("screwOneValue","tmTemp1_Set");
        fields.put("screwTwoValue","tmTemp2_Set");
        fields.put("screwThreeValue","tmTemp3_Set");
        fields.put("screwFourValue","tmTemp4_Set");

        fields.put("screwFiveValue","tmTemp5_Set");
        fields.put("upErrorOne","");
        fields.put("upErrorTwo","");
        fields.put("upErrorThree","");
        fields.put("upErrorFour","");
        fields.put("upErrorFive","");

        fields.put("openOneLocal","tmClpOpnPosi1");
        fields.put("openOneStress","tmClpOpnPress1");
        fields.put("openOneSpeed","tmClpOpnSpeed1");
        fields.put("openTwoLocal","tmClpOpnPosi2");
        fields.put("openTwoStress","tmClpOpnPress2");
        fields.put("openTwoSpeed","tmClpOpnSpeed2");
        fields.put("openThreeLocal","tmClpOpnPosi3");
        fields.put("openThreeStress","tmClpOpnPress3");
        fields.put("openThreeSpeed","tmClpOpnSpeed3");
        fields.put("openFourLocal","tmClpOpnPosi4");
        fields.put("openFourStress","tmClpOpnPress4");
        fields.put("openFourSpeed","tmClpOpnSpeed4");
        fields.put("openFiveLocal","");
        fields.put("openFiveStress","tmClpOpnPres5");
        fields.put("openFiveSpeed","tmClpOpnSpeed5");

        fields.put("closeOneStress","tmClpClsPress1");
        fields.put("closeOneSpeed","tmClpClsSpeed1");
        fields.put("closeTwoLocal","tmClpClsPosi2");
        fields.put("closeTwoStress","tmClpClsPress2");
        fields.put("closeTwoSpeed","tmClpClsSpeed2");
        fields.put("closeThreeLocal","tmClpClsPosi3");
        fields.put("closeThreeStress","tmClpClsPress3");
        fields.put("closeThreeSpeed","tmClpClsSpeed3");
        fields.put("closeLowLocal","tmClpClsPosi4");
        fields.put("closeLowStress","tmClpClsPress4");
        fields.put("closeLowSpeed","tmClpClsSpeed4");
        fields.put("closeHighLocal","tmClpClsPosi5");
        fields.put("closeHighStress","tmClpClsPress5");
        fields.put("closeHighSpeed","tmClpClsSpeed5");
        fields.put("joeWay","");
        fields.put("joeCount","");
        fields.put("pushOutOneLocal","tmEjectAdvPosi1");
        fields.put("pushOutOneStress","tmEjectAdvPress1");
        fields.put("pushOutOneSpeed","tmEjectAdvSpeed1");

        fields.put("pushOutOneDelay","tmEjectAdvDelayTime");
        fields.put("pushOutTwoLocal","tmEjectAdvPosi2");
        fields.put("pushOutTwoStress","tmEjectAdvPress2");
        fields.put("pushOutTwoSpeed","tmEjectAdvSpeed2");
        fields.put("pushOutTwoDelay","");
        fields.put("pushBackOneLocal","tmEjectRetPosi1");
        fields.put("pushBackOneStress","tmEjectRetPress1");
        fields.put("pushBackOneSpeed","tmEjectRetSpeed1");
        fields.put("pushBackOneDelay","tmEjectRetDelayTime");
        fields.put("pushBackTwoLocal","tmEjectRetPosi2");
        fields.put("pushBackTwoStress","tmEjectRetPress2");
        fields.put("pushBackTwoSpeed","tmEjectRetSpeed2");
        fields.put("pushBackTwoDelay","");
    }

    public String dataIsQualified(String qualifiedData, String data){
        //如果工艺卡中的数据为空或者null,返回空串
        if(CheckUtils.isNull(qualifiedData)){
            return "";
        }
        //如果采集到的数据为空或者null,就返回原值
        if(CheckUtils.isNull(data)){
            return qualifiedData;
        }
        String result = qualifiedData + "(" + data + ")";
        String[] split = qualifiedData.split("±");
        double info = Double.parseDouble(data);
        if(split.length == 1){
            if(Double.parseDouble(split[0]) == info){
                result = qualifiedData;
            }
        }else if(split.length == 2){
            double max = Double.parseDouble(split[0]) + Double.parseDouble(split[1]);
            double min = Double.parseDouble(split[0]) - Double.parseDouble(split[1]);
            if(info < max && info > min){
                result = qualifiedData;
            }
        }else {
            result = "参数有问题";
        }
        return result;
    }

    /**
     * 获取属性名数组
     * */
    public String[] getFieldName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 根据属性名获取属性值
     * @param fieldName 属性名称
     * @param o 对象
     * @return
     */
    public Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

        /**
            setter方法
            o:要操作类的对象
            args:属性名
            attributeValue:属性值
         */
    public void setFieldValueByName(Object o,String args,Object attributeValue){
        Class cls = o.getClass();
        //判断该属性是否存在
        Field field = null;
        try {
            field = cls.getDeclaredField(args);
            if(field == null){
                field = cls.getField(args);
            }
            if(field == null){
                return;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        String fieldName = "set"+args.substring(0,1).toUpperCase()+(args.length()>1?args.substring(1):"");
        Method method = null;
        try {
            method = cls.getMethod(fieldName,attributeValue.getClass());
            method.invoke(o,attributeValue);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
