package com.preschool.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue3<K, V1, V2, V3> extends KeyValue2<K, V1, V2> {

    protected V3 value3;

    public KeyValue3(K key, V1 value1, V2 value2, V3 value3) {
        super(key, value1, value2);
        this.value3 = value3;
    }
}

