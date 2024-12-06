package com.java.quiz.javaquizapplication.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String questionNotFound) {
        super(questionNotFound);
    }
}
