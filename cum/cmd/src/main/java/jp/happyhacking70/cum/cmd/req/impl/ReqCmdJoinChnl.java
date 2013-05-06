/**
 * 
 */
package jp.happyhacking70.cum.cmd.req.impl;

import jp.happyhacking70.cum.cmd.CmdActions;
import jp.happyhacking70.cum.cmd.req.ReqCmdChnlAudAbst;
import jp.happyhacking70.cum.cmd.req.ReqCmdFromAudIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class ReqCmdJoinChnl extends ReqCmdChnlAudAbst implements
		ReqCmdFromAudIntf {

	public ReqCmdJoinChnl(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	public ReqCmdJoinChnl(String seshName, String chnlName, String audName) {
		super(seshName, chnlName, audName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.cmd.CmdAbst#setActionName()
	 */
	@Override
	protected void setActionName() {
		actionName = CmdActions.JoinChnl.name();

	}
}
