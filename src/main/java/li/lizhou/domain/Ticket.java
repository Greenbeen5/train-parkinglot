package li.lizhou.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Ticket {

    private final String id;

    public Ticket() {
        id = UUID.randomUUID().toString();
    }
}
