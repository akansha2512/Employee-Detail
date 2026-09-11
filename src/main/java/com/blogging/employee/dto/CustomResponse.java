package com.blogging.employee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;



@Getter
@Setter
@JsonInclude(JsonInclude.Include.ALWAYS)
public class CustomResponse<T> {

    public boolean success;
    private int statusCode;
    private String message;
    private T data;
    private ArrayList<String> errors;
    public CustomResponse(int statusCode, String message){
        this(statusCode, message, null);
    }

    public CustomResponse(int statusCode, String message, T data){
        this.statusCode = statusCode;
        this.message= message;
        this.data = data;
        this.success = (statusCode>=200 && statusCode<300);
    }

//    public CustomResponse(int statusCode, String message, T data, ArrayList<String> errors){
//        this.statusCode = statusCode;
//        this.message = message;
//        this.data = data;
//        this.errors = errors;
//        this.success = (statusCode >= 200 && statusCode < 300);
//    }

}
