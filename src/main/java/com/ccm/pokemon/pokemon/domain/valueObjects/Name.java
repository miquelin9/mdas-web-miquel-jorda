package com.ccm.pokemon.pokemon.domain.valueObjects;

import java.util.Objects;

public class Name {
    public Name(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return name.equals(name1.name);
    }
}
