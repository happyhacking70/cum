/**
 * 
 */
package jp.happyhacking70.cum.excp.prestr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpIllegalChnlStatus extends CumExcpAbst {

	private static final long serialVersionUID = 1L;

	/**
	 * @param chnlName
	 * @param name
	 */
	public CumExcpIllegalChnlStatus(String chnlName, String status) {
		super(chnlName + "/" + status);
	}
}
