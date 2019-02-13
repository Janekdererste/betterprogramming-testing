package com.janeklaudan.betterProgramming.firstExample;


import org.apache.commons.lang3.StringUtils;

public class AbstractEntity {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {

        if (this == other)
            return true;

        if (other instanceof AbstractEntity) {
            AbstractEntity otherEntity = (AbstractEntity) other;
            if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(((AbstractEntity) other).getId()))
                return id.equals(otherEntity.getId());
        }
        return false;
    }
}
