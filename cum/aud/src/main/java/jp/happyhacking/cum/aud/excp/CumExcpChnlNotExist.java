/**
 * 
 */
package jp.happyhacking.cum.aud.excp;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpChnlNotExist extends CumExcpAbst {
	protected String seshName;
	protected String chnlName;

	/**
	 * @param seshName
	 * @param chnlName
	 */
	public CumExcpChnlNotExist(String seshName, String chnlName) {
		super(seshName + "/" + chnlName);
		this.seshName = seshName;
		this.chnlName = chnlName;

	}

	private static final long serialVersionUID = 1L;

}
