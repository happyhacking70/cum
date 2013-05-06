/**
 * 
 */
package jp.happyhacking70.cum.excp;

import jp.happyhacking70.cum.excp.prestr.CumExcpAbst;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpAudExists extends CumExcpAbst {

	protected String seshName;
	protected String chnlName;
	protected String audName;

	/**
	 * @param seshName
	 * @param string
	 * @param audName
	 */
	public CumExcpAudExists(String seshName, String chnlName, String audName) {
		super(seshName + "/" + chnlName + "/" + audName);
		this.seshName = seshName;
		this.chnlName = chnlName;
		this.audName = audName;

	}

	private static final long serialVersionUID = 1L;

}
