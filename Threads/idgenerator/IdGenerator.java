package day12.idgenerator;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    AtomicLong id;

    public IdGenerator() {
        id = new AtomicLong();
    }

    public long getNextId() {
        return id.incrementAndGet();
    }
}
