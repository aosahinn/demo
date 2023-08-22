package com.preschool.demo.data.entity.customer;

import com.preschool.demo.data.entity.BaseEntity;
import com.preschool.demo.data.entity.pack.Pack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER")
public class Customer extends BaseEntity {

    private String firstName;

    private String lastName;

    @OneToOne
    @JoinColumn(name = "pack_id", foreignKey = @ForeignKey(name = "FK_CUSTOMER_PACK_TB_M"))
    private Pack pack;

    private String email;

    private boolean active;

}
