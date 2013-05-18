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
	 * @param rslt
	 */
	void seshJoinFailed(String rslt);

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

}
