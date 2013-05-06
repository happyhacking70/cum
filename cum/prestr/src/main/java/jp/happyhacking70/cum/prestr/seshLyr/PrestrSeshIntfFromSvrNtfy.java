/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import jp.happyhacking70.cum.excp.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnlNotfyIntf;
import jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PrestrSeshIntfFromSvrNtfy {

	/**
	 * callback interface when audience joined session.
	 * <UL>
	 * <LI> {@link PrestrSeshViewIntf#audJoined(String)} should be called.</LI>
	 * </UL>
	 * 
	 * @param audName
	 *            name of audience
	 * @throws CumExcpAudExists
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void audJoinedSesh(String audName) throws CumExcpAudExists,
			CumExcpIllegalSeshStatus;

	/**
	 * callback interface when audience joined channel.
	 * <UL>
	 * <LI><@{link {@link PrestrChnlNotfyIntf#audJoinedChnl(String)} should be
	 * called</LI>
	 * </UL>
	 * 
	 * @param chnlName
	 *            name of channel
	 * @param audName
	 *            name of audience
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudExist
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void audJoinedChnl(String chnlName, String audName)
			throws CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpAudExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus;

	/**
	 * callback interface when audience rejected joining channel.
	 * <UL>
	 * <LI><@{link {@link PrestrChnlNotfyIntf#audRjctedChnl(String)} should be
	 * called</LI>
	 * </UL>
	 * 
	 * @param chnlName
	 *            name of channel
	 * @param audName
	 *            name of audience
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void audRjctedChnl(String chnlName, String audName)
			throws CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus;

	/**
	 * callback interface when audience left channel.
	 * <UL>
	 * <LI><@{link {@link PrestrChnlNotfyIntf#audLftChnl(String)} should be
	 * called</LI>
	 * </UL>
	 * 
	 * @param chnlName
	 *            name of channel
	 * @param audName
	 *            name of audience
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void audLftChnl(String chnlName, String audName)
			throws CumExcpAudNotExist, CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus;

	/**
	 * callback interface when audience left session
	 * <UL>
	 * <LI>notifies all the channel that audience left (since session does not
	 * if audience joined which channel(s), notify all channels.)</LI>
	 * </UL>
	 * 
	 * @param audName
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpIllegalChnlStatuMulti
	 */
	public abstract void audLftSesh(String audName)
			throws CumExcpIllegalChnlStatus, CumExcpAudNotExist,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatuMulti;

	/**
	 * callback interface when connection is disconnected
	 * <UL>
	 * <LI>notify all channel that connection is disconnected</LI>
	 * </UL>
	 * 
	 * 
	 */
	public abstract void discned();

	/**
	 * callback interface when audience is disconnected
	 * <UL>
	 * <LI>notifies all the channel that audience is disconnected (since session
	 * does not if audience joined which channel(s), notify all channels.)</LI>
	 * </UL>
	 * 
	 * @param audName
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpIllegalChnlStatuMulti
	 */
	public abstract void audDiscned(String audName)
			throws CumExcpIllegalSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti;

}