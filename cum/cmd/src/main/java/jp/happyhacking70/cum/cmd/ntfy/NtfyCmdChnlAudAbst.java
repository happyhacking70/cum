/**
 * 
 */
package jp.happyhacking70.cum.cmd.ntfy;

import jp.happyhacking70.cum.cmd.CmdChnlAudAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract public class NtfyCmdChnlAudAbst extends CmdChnlAudAbst implements
		NtfyCmdIntf {

	/**
	 * @param seshName
	 * @param chnlName
	 * @param audName
	 */
	public NtfyCmdChnlAudAbst(String seshName, String chnlType,
			String chnlName, String audName) {
		super(seshName, chnlType, chnlName, audName);
	}

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public NtfyCmdChnlAudAbst(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.cmd.CmdAbst#setCmdType()
	 */
	@Override
	protected void setCmdType() {
		cmdType = CmdTypes.NTFY;
	}

}
