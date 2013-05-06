/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import jp.happyhacking70.cum.excp.prestr.CumExcpAudExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpSeshDiscned;
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
	 * @throws CumExcpAudExist
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpSeshDiscned
	 */
	public abstract void audJoinedChnl(String audName) throws CumExcpAudExist,
			CumExcpIllegalChnlStatus;

	/**
	 * Notifies audience rejected the joining channel.
	 * 
	 * @param audName
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpSeshDiscned
	 */
	public abstract void audRjctedChnl(String audName)
			throws CumExcpIllegalChnlStatus;

	/**
	 * Notifies audience left channel.
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
	 */
	public abstract void audDiscned(String audName)
			throws CumExcpIllegalChnlStatus;

	/**
	 * This channel is closed.
	 * <UL>
	 * <LI>if not {@link ChnlStatus#clsing}, just ignore</LI>
	 * <LI>notify channel view via {@link PrestrChnlViewIntf#chnlClsed()}</LI>
	 * </UL>
	 * *
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeClsed
	 */
	public abstract void chnlClsed() throws CumExcpIllegalChnlStatus;

	/**
	 * This channel is registered.
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void chnlReged() throws CumExcpIllegalChnlStatus;

	/**
	 * session is disconnected
	 * <UL>
	 * <LI>notify channel view via {@link PrestrChnlViewIntf#discned()}</LI>
	 * </UL>
	 */
	void discnded();

	/**
	 * session is under closing
	 * <UL>
	 * <LI>notify channel view via {@link PrestrChnlViewIntf#seshClsing()}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 */
	void seshClsing() throws CumExcpIllegalChnlStatus;

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
