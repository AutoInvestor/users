package io.autoinvestor.domain;

import java.util.List;

public interface UserRepository {
    void save(List<Event<?>> userEvents);
}
