/**
 * 
 */
package jp.happyhacking70.cum.excp.prestr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpAudExist extends CumExcpAbst {

	private static final long serialVersionUID = 1L;

	/**
	 * @param audName
	 */
	public CumExcpAudExist(String chnlName, String audName) {
		super(chnlName + "/" + audName);
	}

}
