/**
 * 
 */
package jp.happyhacking70.cum.cmd.req;

import jp.happyhacking70.cum.cmd.CmdChnlAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract public class ReqCmdChnlAbst extends CmdChnlAbst implements ReqCmdIntf {

	/**
	 * @param seshName
	 * @param chnlType
	 * @param chnlName
	 */
	public ReqCmdChnlAbst(String seshName, String chnlType, String chnlName) {
		super(seshName, chnlType, chnlName);
	}

	/**
	 * @throws CumExcpIllegalCmdDoc
	 * 
	 */
	public ReqCmdChnlAbst(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	@Override
	protected void setCmdType() {
		cmdType = CmdTypes.REQ;

	}

}
