package li.lizhou.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Ticket {

    private String id;

    public Ticket() {
        id = UUID.randomUUID().toString();
    }
}
