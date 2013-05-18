package jp.happyhacking70.cum.cmd;

/**
 * 
 */

import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract public class CmdChnlAbst extends CmdSeshAbst {
	protected String chnlName;
	protected String chnlType;

	public CmdChnlAbst(String seshName, String chnlType, String chnlName) {
		super(seshName);
		this.chnlType = chnlType;
		this.chnlName = chnlName;
	}

	/**
	 * @throws CumExcpIllegalCmdDoc
	 * 
	 */
	public CmdChnlAbst(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);

		setChnlNameFromDoc(doc);
		setChnlTypeFromDoc(doc);
	}

	protected final void setChnlNameFromDoc(Document doc) {
		this.chnlName = getChnlNameFromDoc(doc);
	}

	protected final void setChnlTypeFromDoc(Document doc) {
		this.chnlType = getChnlTypeFromDoc(doc);
	}

	public final String getChnlName() {
		return chnlName;
	}

	public final String getChnlType() {
		return chnlType;
	}

	@Override
	protected void configureDomCocument(Element cmdElem)
			throws CumExcpXMLGenFailed {
		super.configureDomCocument(cmdElem);
		cmdElem.setAttribute("CHNL", getChnlName());
		cmdElem.setAttribute("CHNLTYPE", getChnlType());
	}

	protected static final String getChnlNameFromDoc(Document doc) {
		return doc.getDocumentElement().getChildNodes().item(0).getAttributes()
				.getNamedItem("CHNL").getNodeValue();
	}

	protected static final String getChnlTypeFromDoc(Document doc) {
		return doc.getDocumentElement().getChildNodes().item(0).getAttributes()
				.getNamedItem("CHNLTYPE").getNodeValue();
	}
}
