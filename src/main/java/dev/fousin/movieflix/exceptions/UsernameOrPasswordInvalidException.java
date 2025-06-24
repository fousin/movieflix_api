package dev.fousin.movieflix.exceptions;

public class UsernameOrPasswordInvalidException extends RuntimeException{
    public UsernameOrPasswordInvalidException(String message) {
        super(message);
    }

}
