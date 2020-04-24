package com.hamidsolutions.services.api.users.commons.exception;

public class ApplicationException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ApplicationException() {
        super();
    }

    private String code;
    String errorCode;
    String errorMessage;
    String wsUuid;

    public ApplicationException(Throwable th) {
        super(th);
    }

    public ApplicationException(String code, String message){
        super(message);

    }

    public ApplicationException(String code,String message, Throwable th) {
        super(message,th);
        this.code = code;

    }
    
    public ApplicationException(String errorCode, String wsUuid, String message){
        super(message);
        this.errorCode=errorCode;
        this.wsUuid = wsUuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getWsUuid() {
        return wsUuid;
    }

    public void setWsUuid(String wsUuid) {
        this.wsUuid = wsUuid;
    }

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ApplicationException(String code, String errorCode, String errorMessage, String wsUuid) {
		super();
		this.code = code;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.wsUuid = wsUuid;
	}

}
