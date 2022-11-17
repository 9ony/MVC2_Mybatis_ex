package user.model;
//예외클래스
public class NotUserException extends Exception {
	public NotUserException() {
		super("NotUserException");
	}
	public NotUserException(String msg) {
		super(msg);
	}
}
