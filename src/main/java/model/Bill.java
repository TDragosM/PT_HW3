package model;

import java.time.LocalDateTime;

public record Bill(int id, int clientId, int productId, int quantity, LocalDateTime timestamp) {
    public Bill {
        if (id < 0 || clientId < 0 || productId < 0 || quantity < 0 || timestamp == null) {
            throw new IllegalArgumentException("Invalid bill parameters");
        }
    }

    public int id() {
        return id;
    }

    public int clientId() {
        return clientId;
    }

    public int productId() {
        return productId;
    }

    public int quantity() {
        return quantity;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }
}

