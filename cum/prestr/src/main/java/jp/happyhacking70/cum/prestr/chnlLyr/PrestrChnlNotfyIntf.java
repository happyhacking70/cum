/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnl.ChnlStatus;
import jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PrestrChnlNotfyIntf {

	/**
	 * Notifies audience joined.
	 * 
	 * @param audName
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudExists
	 */
	public abstract void audJoinedChnl(String audName)
			throws CumExcpIllegalChnlStatus, CumExcpAudExists;

	/**
	 * Notifies audience rejected the joining channel.
	 * 
	 * @param audName
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudExists
	 */
	public abstract void audRjctedChnl(String audName)
			throws CumExcpIllegalChnlStatus, CumExcpAudExists;

	/**
	 * Notifies audience left channel.
	 * 
	 * 
	 * @param audName
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void audLftChnl(String audName)
			throws CumExcpIllegalChnlStatus;

	/**
	 * Notifies audience is disconnected.
	 * 
	 * @param audName
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudNotExist
	 */
	public abstract void audDiscned(String audName)
			throws CumExcpIllegalChnlStatus, CumExcpAudNotExist;

	/**
	 * This channel is closed.
	 * <UL>
	 * <LI>if not {@link ChnlStatus#clsing}, just ignore</LI>
	 * <LI>notify channel view via {@link PrestrChnlViewIntf#chnlClsed()}</LI>
	 * </UL>
	 * *
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void chnlClsed() throws CumExcpIllegalChnlStatus;

	/**
	 * This channel is registered.
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 */
	void chnlReged() throws CumExcpIllegalChnlStatus;

	/**
	 * session is disconnected
	 * <UL>
	 * <LI>notify channel view via {@link PrestrChnlViewIntf#discned()}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void discnded() throws CumExcpIllegalChnlStatus;

	/**
	 * session is under closing
	 * <UL>
	 * <LI>notify channel view via {@link PrestrChnlViewIntf#seshClsing()}</LI>
	 * </UL>
	 */
	void seshClsing();

	/**
	 * channel was not closed successfully.
	 * <UL>
	 * <LI>change the status to clsed even failed</LI>
	 * <LI>notify channel view</LI>
	 * </UL>
	 * 
	 * @param rslt
	 * @throws CumExcpIllegalChnlStatus
	 */
	public abstract void clsChnlFailed(String rslt)
			throws CumExcpIllegalChnlStatus;

}
