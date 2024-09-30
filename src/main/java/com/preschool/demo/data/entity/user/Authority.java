package com.preschool.demo.data.entity.user;

import com.preschool.demo.data.entity.BaseEntity;
import com.preschool.demo.type.AuthorityType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

@Entity
@Table(name = "authority", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authority extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, name = "code", length = CODE_LENGTH)
    @NotNull
    private String code;

    public Authority(AuthorityType type) {
        this.code = type.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Authority that = (Authority) o;

        return new EqualsBuilder()
                .append(code, that.code)
                .append(identifier, that.identifier)
                .isEquals();
    }

}
