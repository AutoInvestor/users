package io.autoinvestor.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import io.autoinvestor.exceptions.RiskLevelNotValid;

public class RiskLevel {
    private final int riskLevel;

    RiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    private static void validate(int riskLevel) {
        if (riskLevel < 1 || riskLevel > 4) {
            throw RiskLevelNotValid.with(riskLevel);
        }
    }

    public static RiskLevel empty() {
        return new RiskLevel(-1);
    }

    public static RiskLevel from(int riskLevel) {
        validate(riskLevel);
        return new RiskLevel(riskLevel);
    }

    @JsonValue
    public int value() {
        return this.riskLevel;
    }
}
