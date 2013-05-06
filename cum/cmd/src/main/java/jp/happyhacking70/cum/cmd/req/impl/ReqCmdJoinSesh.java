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
public class ReqCmdJoinSesh extends ReqCmdSeshAudAbst implements
		ReqCmdFromAudIntf {

	/**
	 * @param seshName
	 * @param audName
	 */
	public ReqCmdJoinSesh(String seshName, String audName) {
		super(seshName, audName);
	}

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public ReqCmdJoinSesh(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	@Override
	protected void setActionName() {
		actionName = CmdActions.JoinSesh.name();
	}

}
