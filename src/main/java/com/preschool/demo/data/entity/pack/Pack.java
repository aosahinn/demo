package com.preschool.demo.data.entity.pack;

import com.preschool.demo.data.entity.BaseEntity;
import com.preschool.demo.utils.enums.PackType;
import com.preschool.demo.utils.enums.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER")
public class Pack extends BaseEntity {

    private String description;

    private PeriodType period;

    private PackType type;

    private String email;

    private boolean active;

}
