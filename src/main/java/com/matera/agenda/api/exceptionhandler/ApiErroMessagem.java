package com.matera.agenda.api.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErroMessagem {
	
	private HttpStatus status;
    private List<String> errors;

    public ApiErroMessagem(HttpStatus status, String error) {
        super();
        this.status = status;
        errors = Arrays.asList(error);
    }

}
