/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.prestr.seshLyr.PrestrSeshIntfForChnlView;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class DummyPrestrSeshIntfForChnlView implements
		PrestrSeshIntfForChnlView {

	protected String chnlToClose;
	protected String chnlToSendCmd;
	protected String actionName;
	protected HashMap<String, String> params;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.seshLyr.PrestrSeshIntfForChnlView#clsChnl
	 * (java.lang.String)
	 */
	@Override
	public void clsChnl(String chnlName) {
		chnlToClose = chnlName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfForChnlView#sendChnlCmd
	 * (java.lang.String, java.lang.String, java.util.HashMap)
	 */
	@Override
	public void sendChnlCmd(String chnlName, String actionName,
			HashMap<String, String> params) {
		chnlToSendCmd = chnlName;
		this.actionName = actionName;
		this.params = params;
	}

	public String getChnlToClose() {
		return chnlToClose;
	}

	public String getChnlToSendCmd() {
		return chnlToSendCmd;
	}

	public String getActionName() {
		return actionName;
	}

	public HashMap<String, String> getParams() {
		return params;
	}

}
