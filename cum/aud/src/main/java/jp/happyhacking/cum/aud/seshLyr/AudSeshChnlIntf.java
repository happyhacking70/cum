/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshChnlIntf {

	public abstract void lvChnl(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist;

	/**
	 * @param chnlType
	 * @param chnlName
	 * @param name
	 * @return
	 */
	public abstract ChnlRscIntf fetchRsc(String chnlType, String chnlName,
			String name);

}