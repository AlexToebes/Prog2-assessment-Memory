package com.alextoebes.memorygame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by alextoebes on 12/06/16.
 */
public class UUIDGenerator {
    private static final int UUIDLength = 5;
    private List<String> map;

    private static final UUIDGenerator instance = new UUIDGenerator();

    private UUIDGenerator() {
        map = new ArrayList<>();
    }

    public static String getUUID() {
        String potentialUUID = instance.generate();
        return instance.exists(potentialUUID) ? getUUID() : potentialUUID;
    }

    private boolean exists(String potentialUUID) {
        for (String item:map) if (item.equals(potentialUUID)) return true;
        return false;
    }

    private String generate() {
        return UUID.randomUUID().toString();
    }
}
