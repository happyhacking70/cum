/**
 * 
 */
package jp.happyhacking70.cum.excp.prestr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpSeshCannotBeClsed extends CumExcpAbst {
	private static final long serialVersionUID = 1L;

	String status;

	/**
	 * @param status
	 */
	public CumExcpSeshCannotBeClsed(String status) {
		this.status = status;
	}

}
