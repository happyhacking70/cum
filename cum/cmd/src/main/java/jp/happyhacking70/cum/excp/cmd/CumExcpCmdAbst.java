package jp.happyhacking70.cum.excp.cmd;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract public class CumExcpCmdAbst extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CumExcpCmdAbst() {
		super();
	}

	/**
	 * @param cause
	 */
	public CumExcpCmdAbst(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 */
	public CumExcpCmdAbst(String message) {
		super(message);
	}

	public CumExcpCmdAbst(String message, Throwable cause) {
		super(message, cause);
	}

}
