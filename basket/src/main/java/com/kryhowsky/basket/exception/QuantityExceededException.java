package com.kryhowsky.basket.exception;

public class QuantityExceededException extends RuntimeException {

    public QuantityExceededException(String message) {
        super(message);
    }

}
