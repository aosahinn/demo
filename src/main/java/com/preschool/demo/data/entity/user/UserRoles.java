package com.preschool.demo.data.entity.user;

import com.preschool.demo.data.entity.IdEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "role_id"}),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoles extends IdEntity {

    @NotNull
    @Column(name = "user_id", length = ID_LENGTH)
    private String userId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

}
