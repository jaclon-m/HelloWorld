package com.jaclon.javacore.threadandconcurrent;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jaclon
 * @date 2019/9/16
 */
public enum CountryEnum {

    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"赵"),
    FIVE(5,"魏"),
    SIX(6,"韩");

    CountryEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    @Getter
    @Setter
    Integer code;
    @Getter
    @Setter
    String message;

    public static CountryEnum forEach(int index){
        for(CountryEnum countryEnum: CountryEnum.values()){
            if(index == countryEnum.getCode()){
                return countryEnum;
            }
        }
        return null;
    }
}
