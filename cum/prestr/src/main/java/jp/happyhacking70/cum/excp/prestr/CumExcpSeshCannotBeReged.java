/**
 * 
 */
package jp.happyhacking70.cum.excp.prestr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpSeshCannotBeReged extends CumExcpAbst {
	private static final long serialVersionUID = 1L;

	String status;

	/**
	 * @param status
	 */
	public CumExcpSeshCannotBeReged(String status) {
		this.status = status;
	}

}
