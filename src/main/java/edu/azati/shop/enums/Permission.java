package edu.azati.shop.enums;

import lombok.Getter;

@Getter
public enum Permission {
    Read("read"),
    Write("write");
    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
