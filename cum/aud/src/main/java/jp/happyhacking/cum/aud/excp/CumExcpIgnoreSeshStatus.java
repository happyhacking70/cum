/**
 * 
 */
package jp.happyhacking.cum.aud.excp;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpIgnoreSeshStatus extends CumExcpAbst {

	private static final long serialVersionUID = 1L;

	protected String seshName;
	protected String status;

	/**
	 * @param seshName
	 * @param status
	 */
	public CumExcpIgnoreSeshStatus(String seshName, String status) {
		super(seshName + "/" + status);
		this.seshName = seshName;
		this.status = status;
	}

	/**
	 * @return name of session
	 */
	public String getSeshName() {
		return seshName;
	}

	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

}
