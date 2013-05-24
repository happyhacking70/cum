/**
 * 
 */
package jp.happyhacking.cum.aud.adptrLyr;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * audience adaptor interface
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudAdptrIntf {

	/**
	 * @param seshName
	 *            name of session
	 * @param audName
	 *            name of audience
	 */
	void joinSesh(String seshName, String audName);

	/**
	 * @param seshName
	 *            name of session
	 * @param audName
	 *            name of audience
	 */
	void lvSesh(String seshName, String audName);

	/**
	 * @param seshName
	 *            name of session
	 * @param chnlName
	 *            name of channel
	 * @param chnlType
	 *            channel type
	 * @param audName
	 *            name of audience
	 */
	void joinChnl(String seshName, String chnlType, String chnlName,
			String audName);

	/**
	 * @param seshName
	 *            name of session
	 * @param chnlType
	 *            channel type
	 * @param chnlName
	 *            name of channel
	 * @param audName
	 *            name of audience
	 */
	void lvChnl(String seshName, String chnlType, String chnlName,
			String audName);

	/**
	 * @param seshName
	 *            name of session
	 * @param chnlType
	 *            channel type
	 * @param chnlName
	 *            name of channel
	 * @param audName
	 *            name of audience
	 */
	void rjctChnl(String seshName, String chnlType, String chnlName,
			String audName);

	/**
	 * @param seshName
	 *            name of session
	 * @param chnlType
	 *            channel type
	 * @param chnlName
	 *            name of channel
	 * @param rscName
	 *            name of resource
	 * @return Channel Rsource
	 */
	ChnlRscIntf fetchRsc(String seshName, String chnlType, String chnlName,
			String rscName);
}
