/**
 * 
 */
package jp.happyhacking.cum.aud.audLyr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshViewIntf {

	/**
	 * 
	 */
	void joinSesh();

	/**
	 * 
	 */
	void seshJoined();

	/**
	 * @param rslt
	 */
	void joinSseshFailed(String rslt);

	/**
	 * 
	 */
	void seshLft();

	/**
	 * @param rslt
	 */
	void seshLvFailed(String rslt);

	/**
	 * 
	 */
	void seshClsed();

	/**
	 * 
	 */
	void seshDscned();

	/**
	 * @param chnlType
	 * @return
	 */
	AudChnlViewIntf getChnlView(String chnlType);

	/**
	 * @param chnlName
	 */
	void chnlReged(String chnlType, String chnlName);

	/**
	 * @param chnlName
	 * @param rslt
	 */
	void chnlJoinFailed(String chnlName, String rslt);

	/**
	 * @param chnlName
	 * 
	 */
	void chnlRjcted(String chnlName);

	/**
	 * @param chnlName
	 * @param rslt
	 */
	void chnlRjctFailed(String chnlName, String rslt);

}
