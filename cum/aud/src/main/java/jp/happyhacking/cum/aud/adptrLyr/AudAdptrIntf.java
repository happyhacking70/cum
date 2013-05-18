/**
 * 
 */
package jp.happyhacking.cum.aud.adptrLyr;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudAdptrIntf {

	/**
	 * @param seshName
	 * @param audName
	 */
	void joinSesh(String seshName, String audName);

	/**
	 * @param seshName
	 * @param audName
	 */
	void lvSesh(String seshName, String audName);

	/**
	 * @param seshName
	 * @param chnlName
	 * @param audName
	 */
	void joinChnl(String seshName, String chnlName, String audName);

	/**
	 * @param seshName
	 * @param chnlType
	 * @param chnlName
	 */
	void lvChnl(String seshName, String chnlType, String chnlName);

	/**
	 * @param seshName
	 * @param chnlType
	 * @param chnlName
	 */
	void rjctChnl(String seshName, String chnlType, String chnlName);

}
