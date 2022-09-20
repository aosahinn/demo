package com.preschool.demo.controller.dto;//

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AbstractDto implements Serializable {
    protected String identifier;
    protected Long rowVersion;

    public AbstractDto() {
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Long getRowVersion() {
        return this.rowVersion;
    }

    public void setRowVersion(Long rowVersion) {
        this.rowVersion = rowVersion;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            AbstractDto that = (AbstractDto)o;
            return (new EqualsBuilder()).append(this.identifier, that.identifier).isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(17, 37)).append(this.identifier).toHashCode();
    }
}
