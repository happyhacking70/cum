/**
 * 
 */
package jp.happyhacking70.cum.cmd.res.impl;

import jp.happyhacking70.cum.cmd.CmdActions;
import jp.happyhacking70.cum.cmd.res.ResCmdChnlAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class ResCmdClsChnl extends ResCmdChnlAbst {
	/**
	 * @author happyhacking70@gmail.com
	 * 
	 */
	public enum RsltTypes {
		/** closed Successfully */
		Clsed,
		/** Channle not exists */
		NotExist,
		/** Session does not exits */
		SeshNotExist
	}

	/**
	 * @param seshName
	 * @param chnlName
	 * @param rslt
	 */
	public ResCmdClsChnl(String seshName, String chnlType, String chnlName,
			RsltTypes rslt) {
		super(seshName, chnlType, chnlName, rslt.name());
	}

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public ResCmdClsChnl(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.cmd.CmdAbst#setActionName()
	 */
	@Override
	protected void setActionName() {
		actionName = CmdActions.ClsChnl.name();
	}

}
