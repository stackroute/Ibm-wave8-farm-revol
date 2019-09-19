package com.stackroute.exception;

public class CropAlreadyExistsException extends Exception{
    private String message;
    public CropAlreadyExistsException(String message){
        super(message);
        this.message=message;
    }
    public CropAlreadyExistsException(){

    }
}
