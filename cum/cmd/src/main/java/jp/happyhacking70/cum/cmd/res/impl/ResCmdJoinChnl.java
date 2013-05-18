/**
 * 
 */
package jp.happyhacking70.cum.cmd.res.impl;

import jp.happyhacking70.cum.cmd.CmdActions;
import jp.happyhacking70.cum.cmd.res.ResCmdChnlAudAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class ResCmdJoinChnl extends ResCmdChnlAudAbst {

	/**
	 * @author happyhacking70@gmail.com
	 * 
	 */
	public enum RsltTypes {

		/** Session Not Exist */
		SeshNotExist,
		/** Successfully joined */
		Joined,
		/** Already joined */
		Exists,
		/** Audience is not joiningg session */
		AudNotExist,
		/** Channel not exist */
		ChnlNotExist

	}

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public ResCmdJoinChnl(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	public ResCmdJoinChnl(String seshName, String chnlType, String chnlName,
			String audName, String rslt) {
		super(seshName, chnlType, chnlName, audName, rslt);
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
