/**
 * 
 */
package jp.happyhacking70.cum.cmd.ntfy.impl;

import jp.happyhacking70.cum.cmd.ntfy.NtfyCmdChnlAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class NtfyCmdClsChnl extends NtfyCmdChnlAbst {

	/**
	 * @param seshName
	 * @param chnlName
	 */
	public NtfyCmdClsChnl(String seshName, String chnlName) {
		super(seshName, chnlName);
	}

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public NtfyCmdClsChnl(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	@Override
	protected void setActionName() {
		actionName = "ClsChnl";
	}

}
