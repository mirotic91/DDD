package com.mirotic91.demo.common.model;

public class NameBuilder {

    public static Name build() {
        return build("jonguk", "park");
    }

    public static Name build(final String first, final String last) {
        return Name.builder()
                .first(first)
                .last(last)
                .build();
    }

}