/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import java.util.HashMap;

import jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * Audience Channel Interface called by Session
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudChnlIntfForSesh {

	/**
	 * Channel is closed
	 * <UL>
	 * <LI>change status to {@link AudChnl.Status#clsed}</LI>
	 * <LI>notify channel view via {@link AudChnlViewIntf#chnlClsed()}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void chnlClsed() throws CumExcpIllegalChnlStatus;

	/**
	 * Session is closed
	 * <UL>
	 * <LI>change status to {@link AudChnl.Status#clsed}</LI>
	 * <LI>notify channel view via {@link AudChnlViewIntf#seshClsed()}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void seshClsed() throws CumExcpIllegalChnlStatus;

	/**
	 * Called when session tries to join channel
	 * 
	 * <UL>
	 * <LI>change status to {@link AudChnl.Status#joining}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void chnlJoining() throws CumExcpIllegalChnlStatus;

	/**
	 * joined channel successfully
	 * <UL>
	 * <LI>change status to {@link AudChnl.Status#joined}</LI>
	 * <LI>fetch actual channel resources and replace resources</LI>
	 * <LI>notify channel view via {@link AudChnlViewIntf#chnlJoined()}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void chnlJoined() throws CumExcpIllegalChnlStatus;

	/**
	 * 
	 * Joining Channel Failed
	 * <UL>
	 * <LI>change status to {@link AudChnl.Status#init}</LI>
	 * </UL>
	 * 
	 * @param rslt
	 *            reason of failure
	 * @throws CumExcpIllegalChnlStatus
	 */
	void joinChnlFailed(String rslt) throws CumExcpIllegalChnlStatus;

	/**
	 * called when session tries to reject channel
	 * <UL>
	 * <LI>change status to {@link AudChnl.Status#rjcting}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void chnlRjcting() throws CumExcpIllegalChnlStatus;

	/**
	 * rejected channel successfully
	 * <UL>
	 * <LI>change status to {@link AudChnl.Status#init}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void chnlRjcted() throws CumExcpIllegalChnlStatus;

	/**
	 * Rejecting channel failed
	 * <UL>
	 * <LI>change status to {@link AudChnl.Status#init}</LI>
	 * </UL>
	 * *
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void chnlRjctFailed() throws CumExcpIllegalChnlStatus;

	/**
	 * Left channel successfully
	 * <UL>
	 * <LI>change status to {@link AudChnl.Status#init}</LI>
	 * <LI>notify channel view via {@link AudChnlViewIntf#chnlLvFailed(String)}</LI>
	 * </UL>
	 * *
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void chnlLft() throws CumExcpIllegalChnlStatus;

	/**
	 * Leaving Channel Failed
	 * 
	 * 
	 * @param rslt
	 *            reason of failure
	 * @throws CumExcpIllegalChnlStatus
	 */
	void chnlLvFailed(String rslt) throws CumExcpIllegalChnlStatus;

	/**
	 * Channel Command is received
	 * <UL>
	 * <LI>notify channel view via
	 * {@link AudChnlIntfForSesh#chnlCmdRcved(String, HashMap)}</LI>
	 * </UL>
	 * 
	 * @param actionName
	 *            name of action
	 * @param params
	 *            parameters
	 * @throws CumExcpIllegalChnlStatus
	 */
	void chnlCmdRcved(String actionName, HashMap<String, String> params)
			throws CumExcpIllegalChnlStatus;

	/**
	 * Connection is disconnected
	 * <UL>
	 * <LI>change status to {@link AudChnl.Status#dscned}</LI>
	 * <LI>notify channel view via {@link AudChnlViewIntf#chnlDscned()}</LI>
	 * </UL>
	 * * @throws CumExcpIllegalChnlStatus
	 */
	void chnlDscned() throws CumExcpIllegalChnlStatus;

	/**
	 * Session is leaving
	 * <UL>
	 * <LI>notify channel view via {@link AudChnlViewIntf#seshLving()}</LI>
	 * <LI>change status to {@link AudChnl.Status#init}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void seshLving() throws CumExcpIllegalChnlStatus;

	/**
	 * Get Channel Type
	 * 
	 * @return channel type
	 */
	String getChnlType();

	/**
	 * get channel resources
	 * 
	 * @return resources
	 */
	HashMap<String, ChnlRscIntf> getRsces();

}
