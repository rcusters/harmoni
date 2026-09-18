package be.opcodes.harmoni.core.domain;

import java.util.Objects;

public record Workspace(String id, String name, String description) {

    public Workspace {
        id = requireValue(id, "id");
        name = requireValue(name, "name");
        description = requireValue(description, "description");
    }

    private static String requireValue(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " is required");
        var trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
        return trimmed;
    }
}
