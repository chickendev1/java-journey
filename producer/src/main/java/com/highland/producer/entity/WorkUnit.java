package com.highland.producer.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class WorkUnit {
    private String id;
    private String definition;

    public WorkUnit() {
    }

    @JsonCreator
    public WorkUnit(@JsonProperty("id") String id,
                    @JsonProperty("definition") String definition) {
        this.id = id;
        this.definition = definition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("definition", definition)
                .toString();
    }
}
