/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import java.util.HashMap;

import jp.happyhacking.cum.aud.audLyr.AudSeshViewIntfForSesh;
import jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh;
import jp.happyhacking.cum.aud.excp.CumExcpChnlExists;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * Audience SessionInterface called by Audience adaptor
 * 
 * @author happyhacking70@gmail.com
 * 
 */
/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshAdptrIntf {

	/**
	 * Session is successfully joined
	 * <UL>
	 * <LI>change status to {@link AudSesh.Status#joined}</LI>
	 * <LI>notify session view via {@link AudSeshViewIntfForSesh#seshJoined()}</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void seshJoined() throws CumExcpIllegalSeshStatus;

	/**
	 * joining session is failed
	 * <UL>
	 * <LI>change status to {@link AudSesh.Status#init}</LI>
	 * <LI>notify session view via
	 * {@link AudSeshViewIntfForSesh#joinSseshFailed(String)}</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * 
	 * @param rslt
	 *            reason of failure
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void seshJoinFailed(String rslt)
			throws CumExcpIllegalSeshStatus;

	/**
	 * successfully left session
	 * <UL>
	 * <LI>change status to {@link AudSesh.Status#init}</LI>
	 * <LI>notify session view via {@link AudSeshViewIntfForSesh#seshLft()}</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void seshLft() throws CumExcpIllegalSeshStatus;

	/**
	 * leaving session failed
	 * 
	 * <UL>
	 * <LI>change status to {@link AudSesh.Status#init}</LI>
	 * <LI>notify session view via
	 * {@link AudSeshViewIntfForSesh#lvSeshFailed(String)}</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * *
	 * 
	 * @param rslt
	 *            reason of failure
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void seshLvFailed(String rslt)
			throws CumExcpIllegalSeshStatus;

	/**
	 * Session is closed by presenter
	 * 
	 * <UL>
	 * <LI>change status to {@link AudSesh.Status#clsed}</LI>
	 * <LI>notify session view via {@link AudSeshViewIntfForSesh#seshClsed()}</LI>
	 * <LI>notify all channels view via {@link AudChnlIntfForSesh#seshClsed()}</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * *
	 * 
	 * @throws CumExcpIllegalChnlStatuMulti
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void seshClsed() throws CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus;

	/**
	 * Session is disconnected
	 * 
	 * <UL>
	 * <LI>change status to {@link AudSesh.Status#dscned}</LI>
	 * <LI>notify session view via {@link AudSeshViewIntfForSesh#seshDscned()}</LI>
	 * <LI>notify all channels view via {@link AudChnlIntfForSesh#chnlDscned()}</LI>
	 * <LI>should NOT be synchronized</LI>
	 * </UL>
	 * *
	 * 
	 * @throws CumExcpIllegalChnlStatuMulti
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void seshDscned() throws CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus;

	/**
	 * channel is registered
	 * <UL>
	 * <LI>notify session view via
	 * {@link AudSeshViewIntfForSesh#chnlReged(String, String)}</LI>
	 * <LI>should NOT be synchronized</LI>
	 * </UL>
	 * *
	 * 
	 * @param chnlType
	 *            channel type
	 * @param chnlName
	 *            name of channel
	 * @param rsces
	 *            channel resources (but only name. actual resource should be
	 *            fetched separately by channel when channel is joined)
	 * @throws CumExcpChnlExists
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void chnlReged(String chnlType, String chnlName,
			HashMap<String, ChnlRscIntf> rsces) throws CumExcpChnlExists,
			CumExcpIllegalSeshStatus;

	/**
	 * joined channel successfully
	 * <UL>
	 * <LI>notify channel via {@link AudChnlIntfForSesh#chnlJoined()}</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * 
	 * 
	 * @param chnlName
	 *            name of channel
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void chnlJoined(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	/**
	 * joining channel failed
	 * <UL>
	 * <LI>remove channel</LI>
	 * <LI>notify channel via {@link AudChnlIntfForSesh#joinChnlFailed(String)}</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * *
	 * 
	 * @param chnlName
	 *            name of channel
	 * @param rslt
	 *            reason of failure
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 */
	public abstract void chnlJoinFailed(String chnlName, String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist;

	/**
	 * successfully rejected channel
	 * 
	 * <UL>
	 * <LI>notify channel {@link AudChnlIntfForSesh#chnlRjcted()}
	 * <LI>notify session view via
	 * {@link AudSeshViewIntfForSesh#chnlRjcted(String)}</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * 
	 * @param chnlName
	 *            name of channel
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void chnlRjcted(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	/**
	 * Rjecting channel failed
	 * <UL>
	 * <LI>notify session view via
	 * {@link AudSeshViewIntfForSesh#chnlRjctFailed(String, String)}</LI>
	 * <LI>remove channel</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * *
	 * 
	 * @param chnlName
	 *            name of channel
	 * @param rslt
	 *            reason of failure
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 */
	public abstract void chnlRjctFailed(String chnlName, String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist;

	/**
	 * Left channel successfully
	 * 
	 * <UL>
	 * <LI>notify channel view via {@link AudChnlIntfForSesh#chnlLft()}</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * *
	 * 
	 * @param chnlName
	 *            name of channel
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void chnlLft(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	/**
	 * Leaving channel failed
	 * <UL>
	 * <LI>notify channel view via
	 * {@link AudChnlIntfForSesh#chnlLvFailed(String)}</LI>
	 * <LI>remove channel</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * 
	 * @param chnlName
	 *            name of channel
	 * @param rslt
	 *            reason of failure
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void lvChnlFailed(String chnlName, String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	/**
	 * Channel is closed by presenter
	 * 
	 * <UL>
	 * <LI>notify channel view via {@link AudChnlIntfForSesh#chnlClsed()}</LI>
	 * <LI>remove channel</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * 
	 * @param chnlName
	 *            name of channel
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void chnlClsed(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	/**
	 * Received channel command
	 * <UL>
	 * <LI>notify channel view via
	 * {@link AudChnlIntfForSesh#chnlCmdRcved(String, HashMap)}</LI>
	 * <LI>should be synchronized</LI>
	 * </UL>
	 * 
	 * @param chnlName
	 *            name of channel
	 * @param actioName
	 *            name of action
	 * @param params
	 *            parameters
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void chnlCmdRcved(String chnlName, String actioName,
			HashMap<String, String> params) throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus;
}