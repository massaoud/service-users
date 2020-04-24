package com.hamidsolutions.services.api.users.commons.exception;



import com.hamidsolutions.services.api.users.commons.dto.ErrorDto;

import java.util.ArrayList;
import java.util.List;


public class PartialException extends RuntimeException {
	
	  /**
    *
    */
   private static final long serialVersionUID = 1L;
   String errorCode;
   String field;
   String wsUuid;
   String referenceNo;
   List<ErrorDto> errorDtoList = new ArrayList<>();

   public String getField() {
       return field;
   }

   public void setField(String field) {
       this.field = field;
   }
   
   public String getWsUuid() {
       return wsUuid;
   }

   public void setWsUuid(String wsUuid) {
       this.wsUuid = wsUuid;
   }
   
   public String getReferenceNo() {
       return referenceNo;
   }
   
   public void setReferenceNo(String referenceNo) {
	   this.referenceNo = referenceNo;
   }

   public String getErrorCode() {
       return errorCode;
   }

   public void setErrorCode(String errorCode) {
       this.errorCode = errorCode;
   }

   public List<ErrorDto> getErrorDtoList() {
       return this.errorDtoList;
   }

   public void setErrorDtoList(ErrorDto errorDTO) {
       this.errorDtoList.add(errorDTO);
   }


   public PartialException(String errorCode, String wsUuid, String message, String referenceNo){
       super(message);
       this.errorCode=errorCode;
       this.wsUuid = wsUuid;
       this.referenceNo = referenceNo;
   }
   
   public PartialException(String errorCode, String wsUuid, String message){
       super(message);
       this.errorCode=errorCode;
       this.wsUuid = wsUuid;
   }

}
