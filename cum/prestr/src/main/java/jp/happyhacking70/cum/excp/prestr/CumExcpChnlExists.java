/**
 * 
 */
package jp.happyhacking70.cum.excp.prestr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpChnlExists extends CumExcpAbst {

	protected String seshName;
	protected String chnlName;

	/**
	 * @param seshName
	 * @param chnlName
	 */
	public CumExcpChnlExists(String seshName, String chnlName) {
		super(seshName + "/" + chnlName);
		this.seshName = seshName;
		this.chnlName = chnlName;
	}

	private static final long serialVersionUID = 1L;

}
