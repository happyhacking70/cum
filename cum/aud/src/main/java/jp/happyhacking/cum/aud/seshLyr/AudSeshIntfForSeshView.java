/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import jp.happyhacking.cum.aud.adptrLyr.AudAdptrIntf;
import jp.happyhacking.cum.aud.chnlLyr.AudChnl;
import jp.happyhacking.cum.aud.chnlLyr.AudChnl.Status;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIgnoreChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshIntfForSeshView {

	public abstract void joinSesh() throws CumExcpIllegalSeshStatus;

	public abstract void lvSesh() throws CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus;

	/**
	 * Join Channel<BR>
	 * <UL>
	 * <LI>change channel status {@link Status#joining} via
	 * {@link AudChnl#chnlJoining()}</LI>
	 * <LI>fetch all channel resources and replace to fetched resources</LI>
	 * <LI>join channel {@link AudAdptrIntf#joinChnl(String, String, String)}</LI>
	 * </UL>
	 * <BR>
	 * Implementation should fetch all channel resources before trying to join
	 * channel because channel commands will start arriving just after joining
	 * on server.
	 * 
	 * 
	 * @param chnlName
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void joinChnl(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	public abstract void rjctChnl(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;
}