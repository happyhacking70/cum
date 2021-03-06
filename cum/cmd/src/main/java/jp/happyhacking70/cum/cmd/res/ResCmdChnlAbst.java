/**
 * 
 */
package jp.happyhacking70.cum.cmd.res;

import jp.happyhacking70.cum.cmd.CmdChnlAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public abstract class ResCmdChnlAbst extends CmdChnlAbst implements ResCmdIntf {

	protected String rslt;

	public ResCmdChnlAbst(String seshName, String chnlType, String chnlName,
			String rslt) {
		super(seshName, chnlType, chnlName);
		this.rslt = rslt;
	}

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public ResCmdChnlAbst(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	@Override
	final protected void setCmdType() {
		cmdType = CmdTypes.RES;
	}

	@Override
	public final String getRslt() {
		return rslt;
	}

	@Override
	protected void configureDomCocument(Element cmdElem)
			throws CumExcpXMLGenFailed {
		super.configureDomCocument(cmdElem);
		cmdElem.setAttribute("RSLT", getRslt());
	}

}
