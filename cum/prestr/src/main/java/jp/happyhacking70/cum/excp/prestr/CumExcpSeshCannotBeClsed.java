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
	 * @param name
	 */
	public CumExcpSeshCannotBeClsed(String status) {
		this.status = status;
	}

}
