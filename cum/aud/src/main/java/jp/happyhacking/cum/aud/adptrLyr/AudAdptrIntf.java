/**
 * 
 */
package jp.happyhacking.cum.aud.adptrLyr;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

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
	void joinChnl(String seshName, String chnlType, String chnlName,
			String audName);

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

	/**
	 * @param seshName
	 * @param chnlType
	 * @param chnlName
	 * @param rscName
	 * @return
	 */
	ChnlRscIntf fetchRsc(String seshName, String chnlType, String chnlName,
			String rscName);
}
