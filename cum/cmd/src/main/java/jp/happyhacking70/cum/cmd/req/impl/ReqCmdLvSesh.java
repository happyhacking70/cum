/**
 * 
 */
package jp.happyhacking70.cum.cmd.req.impl;

import jp.happyhacking70.cum.cmd.CmdActions;
import jp.happyhacking70.cum.cmd.req.ReqCmdFromAudIntf;
import jp.happyhacking70.cum.cmd.req.ReqCmdSeshAudAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class ReqCmdLvSesh extends ReqCmdSeshAudAbst implements
		ReqCmdFromAudIntf {

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public ReqCmdLvSesh(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	/**
	 * @param seshName
	 * @param audName
	 */
	public ReqCmdLvSesh(String seshName, String audName) {
		super(seshName, audName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.cmd.CmdAbst#setActionName()
	 */
	@Override
	protected void setActionName() {
		actionName = CmdActions.LvSesh.name();
	}

}
