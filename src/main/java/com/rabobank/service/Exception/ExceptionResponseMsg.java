package com.rabobank.service.Exception;
/**
 * @author 738575
 *
 */
public class ExceptionResponseMsg {
	
  private String exceptionMessage;
 
  public ExceptionResponseMsg(String msg){
    this.exceptionMessage = msg;
  }

/**
 * @return the exceptionMessage
 */
public String getExceptionMessage() {
	return exceptionMessage;
}

/**
 * @param exceptionMessage the exceptionMessage to set
 */
public void setExceptionMessage(String exceptionMessage) {
	this.exceptionMessage = exceptionMessage;
}
  

}