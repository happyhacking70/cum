/**
 * 
 */
package jp.happyhacking70.cum.cmd.req;

import jp.happyhacking70.cum.cmd.CmdChnlAudAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract public class ReqCmdChnlAudAbst extends CmdChnlAudAbst implements
		ReqCmdIntf {

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public ReqCmdChnlAudAbst(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	/**
	 * @param seshName
	 * @param chnlType
	 * @param chnlName
	 * @param audName
	 */
	public ReqCmdChnlAudAbst(String seshName, String chnlType, String chnlName,
			String audName) {
		super(seshName, chnlType, chnlName, audName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.cmd.CmdAbst#setCmdType()
	 */
	@Override
	protected void setCmdType() {
		cmdType = CmdTypes.REQ;

	}
}
