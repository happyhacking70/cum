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
	 * @param reason
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

	/**
	 * @param audName
	 */
	void audDscned(String audName);

	void audLft(String audName);

	void dscned();

}
