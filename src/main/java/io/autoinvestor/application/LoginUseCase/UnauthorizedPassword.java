package io.autoinvestor.application.LoginUseCase;

public class UnauthorizedPassword extends RuntimeException {
    private UnauthorizedPassword(String message) {
        super(message);
    }

    public static UnauthorizedPassword with () {
      String messageException = "Invalid password";
      return new UnauthorizedPassword(messageException);
    }
}
