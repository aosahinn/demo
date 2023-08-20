package com.preschool.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue2<K, V1, V2> extends KeyValue1<K, V1> {


    protected V2 value2;

    public KeyValue2(K key, V1 value1, V2 value2) {
        super(key, value1);
        this.value2 = value2;
    }

}

