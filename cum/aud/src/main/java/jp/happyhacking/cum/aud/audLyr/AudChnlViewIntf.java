/**
 * 
 */
package jp.happyhacking.cum.aud.audLyr;

import java.util.HashMap;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudChnlViewIntf {
	void chnlJoined();

	void chnlClsed();

	void seshClsed();

	/**
	 * @param actionName
	 * @param params
	 */
	void chnlCmdRcved(String actionName, HashMap<String, String> params);

	/**
	 * 
	 */
	void chnlLeft();

	/**
	 * @param rslt
	 */
	void chnlLvFailed(String rslt);

	/**
	 * 
	 */
	void chnlDscned();

	/**
	 * 
	 */
	void seshLving();
}
