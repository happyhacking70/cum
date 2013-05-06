/**
 * 
 */
package jp.happyhacking70.cum.cmd.res;

import jp.happyhacking70.cum.cmd.CmdSeshAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract public class ResCmdSeshAbst extends CmdSeshAbst implements ResCmdIntf {

	protected String rslt;

	public ResCmdSeshAbst(String seshName, String rslt) {
		super(seshName);
		this.rslt = rslt;
	}

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public ResCmdSeshAbst(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	@Override
	protected void setCmdType() {
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
