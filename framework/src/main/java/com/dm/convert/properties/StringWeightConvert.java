package com.dm.convert.properties;

import com.dm.common.pojo.Weight;
import com.dm.enums.MassUnit;
import org.springframework.core.convert.converter.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringWeightConvert implements Converter<String, Weight> {

    /**
     * 通过正则匹配出数字
     * */
    @Override
    public Weight convert(String s) {
        String regex = "^[1-9]\\d*\\.?\\d*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            String group = matcher.group();
            double mass = Double.parseDouble(group);
            String unit = s.substring(group.length(), s.length());
            MassUnit massUnit = MassUnit.valueOf(unit);
            Weight weight = new Weight();
            weight.setWeight(mass);
            weight.setMassUnit(massUnit);
            return weight;
        }else {
            return new Weight();
        }
    }
}
