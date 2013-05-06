/**
 * 
 */
package jp.happyhacking70.cum.prestr.prestrLyr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PrestrSeshViewIntf {

	/**
	 * 
	 */
	void seshReged();

	/**
	 * @param name
	 */
	void regSeshFailed(String reason);

	/**
	 * 
	 */
	void seshClsed();

	/**
	 * @param rslt
	 */
	void clsSeshFailed(String rslt);

	/**
	 * @param audName
	 */
	void audJoined(String audName);

	/**
	 * @param rslt
	 */
	void regChnlFailed(String rslt);

}
