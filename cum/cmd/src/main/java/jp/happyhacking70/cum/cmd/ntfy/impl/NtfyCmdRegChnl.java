/**
 * 
 */
package jp.happyhacking70.cum.cmd.ntfy.impl;

import java.util.ArrayList;

import jp.happyhacking70.cum.cmd.CmdActions;
import jp.happyhacking70.cum.cmd.ntfy.NtfyCmdChnlRscAbst;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class NtfyCmdRegChnl extends NtfyCmdChnlRscAbst {
	protected String chnlType;

	public NtfyCmdRegChnl(String seshName, String chnlType, String chnlName,
			ArrayList<ChnlRscIntf> rsces) {
		super(seshName, chnlType, chnlName, rsces);
		this.chnlType = chnlType;
	}

	/**
	 * @param seshName
	 * @param chnlName
	 */
	public NtfyCmdRegChnl(String seshName, String chnlType, String chnlName) {
		super(seshName, chnlType, chnlName);
		this.chnlType = chnlType;
	}

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public NtfyCmdRegChnl(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	@Override
	protected void setActionName() {
		actionName = CmdActions.RegChnl.name();
	}

}
