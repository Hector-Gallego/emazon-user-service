package com.emazon.emazonuserservice.domain.model;

public class WarehouseAssistant extends User {
    protected WarehouseAssistant(Builder builder) {
        super(builder);
    }

    public static class Builder extends User.Builder {
        @Override
        public WarehouseAssistant build() {
            return new WarehouseAssistant(this);
        }


    }
}