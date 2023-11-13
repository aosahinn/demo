package com.preschool.demo.data.entity.subscription;

import com.preschool.demo.data.entity.BaseEntity;
import com.preschool.demo.data.entity.company.Company;
import com.preschool.demo.data.entity.customer.Customer;
import com.preschool.demo.data.entity.pack.Pack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUBSCRIPTION")
public class Subscription extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "pack_id", foreignKey = @ForeignKey(name = "FK_SUBCRIPTION_PACK_TB_M"))
    private Pack pack;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

}
