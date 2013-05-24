/**
 * 
 */
package jp.happyhacking.cum.aud.excp;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpIllegalChnlStatus extends CumExcpAbst {

	private static final long serialVersionUID = 1L;

	protected String chnlName;
	protected String status;

	/**
	 * @param chnlName
	 * @param status
	 */
	public CumExcpIllegalChnlStatus(String chnlName, String status) {
		super(chnlName + "/" + status);
		this.chnlName = chnlName;
		this.status = status;
	}

	/**
	 * @return name of channel
	 */
	public String getChnlName() {
		return chnlName;
	}

	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

}
