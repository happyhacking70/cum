/**
 * 
 */
package jp.happyhacking70.cum.excp.prestr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpAudNotExist extends CumExcpAbst {
	protected String seshName;
	protected String chnlName;
	protected String audName;

	public CumExcpAudNotExist(String seshName, String chnlName, String audName) {
		super(seshName + "/" + chnlName + "/" + audName);
		this.seshName = seshName;
		this.chnlName = chnlName;
		this.audName = audName;
	}

	private static final long serialVersionUID = 1L;

}
