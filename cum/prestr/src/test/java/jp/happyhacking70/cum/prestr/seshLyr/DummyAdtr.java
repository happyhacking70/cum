/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.prestr.adptrLyr.PrestrAdptrIntf;
import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnlIntfFromChnlView;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class DummyAdtr implements PrestrAdptrIntf {

	protected String seshNameToReg = null;
	protected String seshNameToCls = null;
	protected String seshNameToRegChnl = null;
	protected PrestrChnlIntfFromChnlView chnl = null;
	protected HashMap<String, ChnlRscIntf> rsces = null;
	protected String seshNameToClsChnl = null;
	protected String chnlNameToCls = null;
	protected String seshNameToSendCmd = null;
	protected String chnlNameToSendCmd = null;
	protected HashMap<String, String> params = null;
	protected String chnlNameToReg = null;

	public String getSeshNameToReg() {
		return seshNameToReg;
	}

	public String getSeshNameToCls() {
		return seshNameToCls;
	}

	public String getSeshNameToRegChnl() {
		return seshNameToRegChnl;
	}

	public PrestrChnlIntfFromChnlView getChnl() {
		return chnl;
	}

	public HashMap<String, ChnlRscIntf> getRsces() {
		return rsces;
	}

	public String getSeshNameToClsChnl() {
		return seshNameToClsChnl;
	}

	public String getChnlNameToCls() {
		return chnlNameToCls;
	}

	public String getSeshNameToSendCmd() {
		return seshNameToSendCmd;
	}

	public String getChnlNameToSendCmd() {
		return chnlNameToSendCmd;
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public String getChnlNameToReg() {
		return chnlNameToReg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.adptrLyr.PrestrAdptrIntf#regSesh(java.lang
	 * .String)
	 */
	@Override
	public void regSesh(String seshName) {
		seshNameToReg = seshName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.adptrLyr.PrestrAdptrIntf#clsSesh(java.lang
	 * .String)
	 */
	@Override
	public void clsSesh(String seshName) {
		seshNameToCls = seshName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.adptrLyr.PrestrAdptrIntf#regChnl(java.lang
	 * .String, jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnlIntfFromChnlView,
	 * java.util.HashMap)
	 */
	@Override
	public void regChnl(String seshName, String chnlName,
			PrestrChnlIntfFromChnlView chnl, HashMap<String, ChnlRscIntf> rsces) {
		seshNameToRegChnl = seshName;
		chnlNameToReg = chnlName;
		this.chnl = chnl;
		this.rsces = rsces;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.adptrLyr.PrestrAdptrIntf#clsChnl(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void clsChnl(String seshName, String chnlName) {
		seshNameToClsChnl = seshName;
		chnlNameToCls = chnlName;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.adptrLyr.PrestrAdptrIntf#sendChnlCmd(java
	 * .lang.String, java.lang.String, java.util.HashMap)
	 */
	@Override
	public void sendChnlCmd(String seshName, String chnlName,
			HashMap<String, String> params) {
		seshNameToSendCmd = seshName;
		chnlNameToSendCmd = chnlName;
		this.params = params;
	}

}
