package com.preschool.demo.Data.Entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.style.ToStringCreator;

@MappedSuperclass
@Inheritance(
        strategy = InheritanceType.TABLE_PER_CLASS
)
@Data
public abstract class IdEntity implements Serializable {
    protected static final int ID_LENGTH = 50;
    protected static final int CODE_LENGTH = 250;
    protected static final int NAME_LENGTH = 250;
    protected static final int USERNAME_LENGTH = 250;
    protected static final int STATUS_LENGTH = 50;
    protected static final int LANG_CODE_LENGTH = 5;
    protected static final int COUNTRY_CODE_LENGTH = 5;
    protected static final int DESC_LENGTH = 2000;
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "system-uuid"
    )
    @GenericGenerator(
            name = "system-uuid",
            strategy = "uuid2"
    )
    @Column(
            name = "id",
            length = 50
    )
    protected String identifier;

    protected IdEntity() {
    }
}
