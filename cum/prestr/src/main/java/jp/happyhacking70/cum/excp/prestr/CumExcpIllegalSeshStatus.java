/**
 * 
 */
package jp.happyhacking70.cum.excp.prestr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpIllegalSeshStatus extends CumExcpAbst {

	private static final long serialVersionUID = 1L;

	/**
	 * @param seshName
	 * @param status
	 */
	public CumExcpIllegalSeshStatus(String seshName, String status) {
		super(seshName + "/" + status);
	}
}
