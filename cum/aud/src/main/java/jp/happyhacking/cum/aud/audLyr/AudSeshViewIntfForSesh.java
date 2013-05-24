/**
 * 
 */
package jp.happyhacking.cum.aud.audLyr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshViewIntfForSesh {

	/**
	 * callback when joined session successfully
	 */
	public abstract void seshJoined();

	/**
	 * callback when joining session failed
	 * 
	 * @param rslt
	 *            reason of failure
	 */
	public abstract void joinSseshFailed(String rslt);

	/**
	 * callback when left session successfully
	 */
	public abstract void seshLft();

	/**
	 * 
	 * callback when leaving session failed
	 * 
	 * @param rslt
	 *            reason of failure
	 */
	public abstract void lvSeshFailed(String rslt);

	/**
	 * callback when session is closed by presenter
	 */
	public abstract void seshClsed();

	/**
	 * callback when session is disconnected
	 * <UL>
	 * <LI>should NOT be synchronized</LI>
	 * </UL>
	 */
	public abstract void seshDscned();

	/**
	 * Provide proper channel view to session
	 * 
	 * @param chnlType
	 *            channel type
	 * @return channel view
	 */
	public abstract AudChnlViewIntf getChnlView(String chnlType);

	/**
	 * callback when channel is registered on server
	 * 
	 * @param chnlType
	 *            channel type
	 * @param chnlName
	 *            name of channel
	 */
	public abstract void chnlReged(String chnlType, String chnlName);

	/**
	 * callback when joining channel failed
	 * 
	 * @param chnlName
	 *            name of channel
	 * @param rslt
	 *            reason of failure
	 */
	public abstract void chnlJoinFailed(String chnlName, String rslt);

	/**
	 * callback when rejected channel successfully
	 * 
	 * 
	 * @param chnlName
	 *            name of channel
	 * 
	 */
	public abstract void chnlRjcted(String chnlName);

	/**
	 * @param chnlName
	 *            name of channel
	 * @param rslt
	 *            reason of failure
	 */
	public abstract void chnlRjctFailed(String chnlName, String rslt);

}