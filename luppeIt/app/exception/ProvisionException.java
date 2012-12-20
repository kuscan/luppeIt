package exception;

public class ProvisionException extends Exception {

	private String exceptionCode;
	private String exceptionMessage;
	
	public ProvisionException() {
		super();
		this.setExceptionCode("00000");
		this.setExceptionMessage("Provision Exception Message");
	}
	
	public ProvisionException(String code, String message) {
		super();
		this.setExceptionCode(code);
		this.setExceptionMessage(message);
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
}
