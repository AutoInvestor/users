package io.autoinvestor.domain.users;

import com.fasterxml.jackson.annotation.JsonValue;
import io.autoinvestor.exceptions.RiskLevelNotValid;

public class RiskLevel {
    private final Integer riskLevel;

    public RiskLevel(Integer riskLevel) {
        validate(riskLevel);
        this.riskLevel = riskLevel;
    }

    private static void validate(Integer riskLevel) {

        if (riskLevel < 1 || riskLevel > 4) {
            throw RiskLevelNotValid.with(riskLevel);
        }
    }

    @JsonValue
    public Integer value() {
        return this.riskLevel;
    }
}
