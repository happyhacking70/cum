/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.excp;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpRscNotExist extends CumExcpSeshChnlRscAbst {

	private static final long serialVersionUID = 1L;

	/**
	 * @param seshName
	 * @param e
	 */
	public CumExcpRscNotExist(String seshName, CumExcpSeshChnlRscAbst e) {
		super(seshName, e);
	}

	/**
	 * @param chnlName
	 * @param rscName
	 */
	public CumExcpRscNotExist(String chnlName, String rscName) {
		super(chnlName, rscName);
	}

}
