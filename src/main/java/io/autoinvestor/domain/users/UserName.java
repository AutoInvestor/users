package io.autoinvestor.domain.users;

public class UserName {
    private final String name;

    public UserName(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserName that))
            return false;
        return name.equals(that.name);
    }
}
