package io.autoinvestor.exceptions;

public class EmailNotValid extends RuntimeException {
    private EmailNotValid(String message) {
        super(message);
    }

    public static EmailNotValid with (String email) {
      String exceptionMessage = "Email " + email + " is not valid";
      return new EmailNotValid(exceptionMessage);
    }
}
