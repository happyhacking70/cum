/**
 * 
 */
package jp.happyhacking70.cum.excp.prestr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpAudNotExist extends CumExcpAbst {

	private static final long serialVersionUID = 1L;

	protected String seshName;
	protected String chnlName;
	protected String audName;

	/**
	 * @param seshName
	 * @param chnlName
	 * @param audName
	 */
	public CumExcpAudNotExist(String seshName, String chnlName, String audName) {
		super(seshName + "/" + chnlName + "/" + audName);
		this.seshName = seshName;
		this.chnlName = chnlName;
		this.audName = audName;

	}

	/**
	 * @param chnlName
	 * @param audName
	 */
	public CumExcpAudNotExist(String chnlName, String audName) {
		super("" + "/" + chnlName + "/" + audName);
		this.chnlName = chnlName;
		this.audName = audName;

	}

	public String getSeshName() {
		return seshName;
	}

	public String getChnlName() {
		return chnlName;
	}

	public String getAudName() {
		return audName;
	}

}
