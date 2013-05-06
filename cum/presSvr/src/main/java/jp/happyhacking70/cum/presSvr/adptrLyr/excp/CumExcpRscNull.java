/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.excp;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpRscNull extends CumExcpSeshChnlAbst {

	private static final long serialVersionUID = 1L;

	/**
	 * @param seshName
	 * @param e
	 */
	public CumExcpRscNull(String seshName, CumExcpSeshChnlAbst e) {
		super(seshName, e);

	}

	/**
	 * @param chnlName
	 */
	public CumExcpRscNull(String chnlName) {
		super(chnlName);

	}

}
