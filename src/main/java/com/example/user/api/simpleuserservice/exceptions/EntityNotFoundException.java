package com.example.user.api.simpleuserservice.exceptions;

public class EntityNotFoundException extends Exception{

    private static final String ERR_MESSAGE = "Resource '%s' in object of class '%s' not found";


    public EntityNotFoundException(final String id, final Class clazz){
        super(String.format(ERR_MESSAGE, id, clazz.getSimpleName()));
    }

}
