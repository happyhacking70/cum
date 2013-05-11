/**
 * 
 */
package jp.happyhacking70.cum.prestr.adptrLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnlIntfFromChnlView;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PrestrAdptrIntf {

	/**
	 * @param seshName
	 */
	void regSesh(String seshName);

	/**
	 * @param seshName
	 * 
	 */
	void clsSesh(String seshName);

	/**
	 * @param chnlName
	 * @param chnl
	 * @param rsces
	 */
	void regChnl(String seshName, String chnlName,
			PrestrChnlIntfFromChnlView chnl, HashMap<String, ChnlRscIntf> rsces);

	/**
	 * @param seshName
	 * @param chnlName
	 */
	void clsChnl(String seshName, String chnlName);

	/**
	 * @param seshName
	 * @param chnlName
	 * @param params
	 */
	void sendChnlCmd(String seshName, String chnlName,
			HashMap<String, String> params);

}
