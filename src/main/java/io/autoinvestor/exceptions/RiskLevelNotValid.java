package io.autoinvestor.exceptions;

public class RiskLevelNotValid extends RuntimeException {
    private RiskLevelNotValid(String message) {
        super(message);
    }

    public static RiskLevelNotValid with(Integer riskLevel) {
        String exceptionMessage = "Risk level " + riskLevel + " is not valid";
        return new RiskLevelNotValid(exceptionMessage);
    }
}
