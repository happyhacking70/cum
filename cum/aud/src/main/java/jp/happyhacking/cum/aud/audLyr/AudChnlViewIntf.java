/**
 * 
 */
package jp.happyhacking.cum.aud.audLyr;

import java.util.HashMap;

/**
 * Interface of Audience Channel View
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudChnlViewIntf {
	/**
	 * callback when channel is successfully joined
	 */
	void chnlJoined();

	/**
	 * callback when channel is closed
	 */
	void chnlClsed();

	/**
	 * callback when session is closed
	 * <UL>
	 * <LI>all activities are supposed to be finished</LI>
	 * </UL>
	 */
	void seshClsed();

	/**
	 * callback when channel command is received
	 * 
	 * @param actionName
	 *            name of action
	 * @param params
	 *            parameters
	 */
	void chnlCmdRcved(String actionName, HashMap<String, String> params);

	/**
	 * callback when successfully left channel
	 */
	void chnlLft();

	/**
	 * callback when leaving channel failed
	 * 
	 * @param rslt
	 *            reason of failiur
	 */
	void chnlLvFailed(String rslt);

	/**
	 * callback when session is disconnected
	 * <UL>
	 * <LI>all activities are supposed to be finished</LI>
	 * </UL>
	 */
	void chnlDscned();

	/**
	 * callback when session is leaving
	 * <UL>
	 * <LI>all activities are supposed to be finished</LI>
	 * </UL>
	 */
	void seshLving();
}
