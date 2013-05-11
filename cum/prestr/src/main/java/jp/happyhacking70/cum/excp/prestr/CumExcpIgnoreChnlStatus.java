/**
 * 
 */
package jp.happyhacking70.cum.excp.prestr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpIgnoreChnlStatus extends CumExcpAbst {

	private static final long serialVersionUID = 1L;

	protected String chnlName;
	protected String status;

	/**
	 * @param chnlName
	 * @param status
	 */
	public CumExcpIgnoreChnlStatus(String chnlName, String status) {
		super(chnlName + "/" + status);
		this.chnlName = chnlName;
		this.status = status;
	}

	public String getChnlName() {
		return chnlName;
	}

	public String getStatus() {
		return status;
	}

}
