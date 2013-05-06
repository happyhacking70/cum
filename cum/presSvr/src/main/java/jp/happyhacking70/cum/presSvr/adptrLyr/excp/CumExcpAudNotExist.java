/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.excp;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpAudNotExist extends CumExcpSeshChnlAudAbst {

	private static final long serialVersionUID = 1L;

	/**
	 * @param seshName
	 * @param e
	 */
	public CumExcpAudNotExist(String seshName, CumExcpSeshChnlAudAbst e) {
		super(seshName, e);
	}

	/**
	 * @param chnlName
	 * @param audName
	 */
	public CumExcpAudNotExist(String chnlName, String audName) {
		super(chnlName, audName);
	}

	/**
	 * @param seshName
	 * @param chnlName
	 * @param audName
	 */
	public CumExcpAudNotExist(String seshName, String chnlName, String audName) {
		super(seshName, chnlName, audName);
	}

}
