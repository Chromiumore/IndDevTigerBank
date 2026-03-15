package me.chromiumore.tigerbank.exception;

public class UnsupportedParameterException extends CreateEntityException{
    public UnsupportedParameterException() {
        super("Given parameter is unsupported by factory");
    }
}
