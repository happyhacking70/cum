/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import jp.happyhacking.cum.aud.adptrLyr.AudAdptrIntf;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * Audience session interface called by channel
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshChnlIntf {

	/**
	 * Leave channel
	 * <UL>
	 * <LI>ask adaptor to leave channel via
	 * {@link AudAdptrIntf#lvChnl(String, String, String, String)}</LI>
	 * </UL>
	 * 
	 * @param chnlName
	 *            name of channel
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 */
	public abstract void lvChnl(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist;

	/**
	 * Fetch channel resource
	 * <UL>
	 * <LI>ask adaptor to fecth channel resource view
	 * {@link AudAdptrIntf#fetchRsc(String, String, String, String)}</LI>
	 * </UL>
	 * 
	 * @param chnlType
	 *            channel type
	 * @param chnlName
	 *            name of channel
	 * @param name
	 *            name of resource
	 * @return channel resource
	 */
	public abstract ChnlRscIntf fetchRsc(String chnlType, String chnlName,
			String name);

}