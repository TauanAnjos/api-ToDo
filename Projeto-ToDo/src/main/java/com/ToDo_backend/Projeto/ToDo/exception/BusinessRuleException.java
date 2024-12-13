package com.ToDo_backend.Projeto.ToDo.exception;

public class BusinessRuleException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public BusinessRuleException(String msg){
        super(msg);
    }
    public BusinessRuleException(String msg, Throwable cause){
        super(msg, cause);
    }
}
