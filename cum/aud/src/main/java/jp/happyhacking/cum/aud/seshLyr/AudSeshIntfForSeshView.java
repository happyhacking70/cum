/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import jp.happyhacking.cum.aud.adptrLyr.AudAdptrIntf;
import jp.happyhacking.cum.aud.chnlLyr.AudChnl;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

/**
 * Audience Session Interface called by session view
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshIntfForSeshView {

	/**
	 * Join Session
	 * <UL>
	 * <LI>change status to {@link AudSesh.Status#joining}</LI>
	 * <LI>ask adaptor to join channel via
	 * {@link AudAdptrIntf#joinSesh(String, String)}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void joinSesh() throws CumExcpIllegalSeshStatus;

	/**
	 * Leave Session
	 * <UL>
	 * <LI>change status to {@link AudSesh.Status#lving}</LI>
	 * <LI>ask adaptor to leave channel via
	 * {@link AudAdptrIntf#lvSesh(String, String)}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void lvSesh() throws CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus;

	/**
	 * Join Channel<BR>
	 * <UL>
	 * <LI>change channel status via {@link AudChnl#chnlJoining()}</LI>
	 * <LI>join channel
	 * {@link AudAdptrIntf#joinChnl(String, String, String, String)}</LI>
	 * </UL>
	 * 
	 * @param chnlName
	 *            name of channel
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void joinChnl(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	/**
	 * Reject Channel
	 * 
	 * <UL>
	 * <LI>change channel status via {@link AudChnl#chnlRjcting()}</LI>
	 * <LI>Reject channel
	 * {@link AudAdptrIntf#rjctChnl(String, String, String, String)}</LI>
	 * </UL>
	 * 
	 * @param chnlName
	 *            name of channel
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void rjctChnl(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;
}