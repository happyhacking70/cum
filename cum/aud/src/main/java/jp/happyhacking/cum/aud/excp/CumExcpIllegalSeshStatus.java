/**
 * 
 */
package jp.happyhacking.cum.aud.excp;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpIllegalSeshStatus extends CumExcpAbst {

	private static final long serialVersionUID = 1L;

	protected String seshName;
	protected String status;

	/**
	 * @param chnlName
	 * @param status
	 */
	public CumExcpIllegalSeshStatus(String seshName, String status) {
		super(seshName + "/" + status);
		this.seshName = seshName;
		this.status = status;
	}

	public String getSeshName() {
		return seshName;
	}

	public String getStatus() {
		return status;
	}

}
