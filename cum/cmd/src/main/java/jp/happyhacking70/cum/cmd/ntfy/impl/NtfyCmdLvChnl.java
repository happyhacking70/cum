/**
 * 
 */
package jp.happyhacking70.cum.cmd.ntfy.impl;

import jp.happyhacking70.cum.cmd.CmdActions;
import jp.happyhacking70.cum.cmd.ntfy.NtfyCmdChnlAudAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class NtfyCmdLvChnl extends NtfyCmdChnlAudAbst {

	/**
	 * @param seshName
	 * @param chnlType
	 * @param chnlName
	 * @param audName
	 */
	public NtfyCmdLvChnl(String seshName, String chnlType, String chnlName,
			String audName) {
		super(seshName, chnlType, chnlName, audName);
	}

	public NtfyCmdLvChnl(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.cmd.CmdAbst#setActionName()
	 */
	@Override
	protected void setActionName() {
		actionName = CmdActions.LvChnl.name();
	}
}
