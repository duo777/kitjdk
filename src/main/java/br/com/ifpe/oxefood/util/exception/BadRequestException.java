package br.com.ifpe.oxefood.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 977747020900311307L;

    public BadRequestException(String msg) {
	super(String.format(msg));
    }

}