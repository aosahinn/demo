package com.preschool.demo.data.entity.subscription;

import com.preschool.demo.data.entity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Table(name= "SUBSCRIPTION")
public class Subscription extends BaseEntity {

    //TODO: COMPANY ENTITY'SI OLUSTURULUNCA TANIMLANACAK
    @Column(name = "company_id", nullable = false)
    private String companyId;

    //TODO: PACKAGE ENTITY'SI OLUSTURULUNCA TANIMLANACAK
    @Column(name = "package_id")
    private String packageId;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    public Subscription() {

    }
}
