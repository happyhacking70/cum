/**
 * 
 */
package jp.happyhacking.cum.aud.audLyr;

import jp.happyhacking.cum.aud.seshLyr.AudSeshIntfForSeshView;

/**
 * Audience Session View Interface
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshViewIntf {

	/**
	 * Join Session
	 * <UL>
	 * <LI>ask audience session to join session via
	 * {@link AudSeshIntfForSeshView#joinSesh()}</LI>
	 * </UL>
	 */
	void joinSesh();

	/**
	 * Leave Session
	 * <UL>
	 * <LI>ask audience session to leave session via
	 * {@link AudSeshIntfForSeshView#lvSesh()}</LI>
	 * </UL>
	 */
	void lvSesh();

	/**
	 * Join Channel
	 * <UL>
	 * <LI>ask audience session to join channel via
	 * {@link AudSeshIntfForSeshView#joinChnl(String)}</LI>
	 * </UL>
	 */
	void joinChnl(String chnlName);

	/**
	 * Reject Channel
	 * <UL>
	 * <LI>ask audience session to reject channel via
	 * {@link AudSeshIntfForSeshView#rjctChnl(String)}</LI>
	 * </UL>
	 */
	void rjctChnl(String chnlName);
}
