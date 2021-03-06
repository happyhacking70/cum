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
public class ResCmdLvChnl extends ResCmdChnlAudAbst {
	/**
	 * @author happyhacking70@gmail.com
	 * 
	 */
	public enum RsltTypes {
		/** Session does not exist */
		SeshNotExist,
		/** Channel does not exist */
		ChnlNotExist,
		/** Audience is not joinning the channel */
		NotExist,
		/** audience left successfully */
		Left

	}

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public ResCmdLvChnl(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	/**
	 * @param seshName
	 * @param chnlName
	 * @param audName
	 * @param rslt
	 */
	public ResCmdLvChnl(String seshName, String chnlType, String chnlName,
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
		actionName = CmdActions.LvChnl.name();

	}

}
