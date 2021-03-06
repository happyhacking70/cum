/**
 * 
 */
package jp.happyhacking70.cum.cmd.ntfy.impl;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.NtfyCmdChnlTestAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.junit.BeforeClass;
import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class NtfyCmdClsChnlTest extends NtfyCmdChnlTestAbst {
	/**
	 * @throws IOException
	 */
	public NtfyCmdClsChnlTest() throws IOException {
		super();
	}

	static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"ClsChnl\" CHNL=\"testChannel\" CHNLTYPE=\"testChannelType\" SESH=\"testSession\" TYPE=\"NTFY\"/></CUM>";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(xml);
	}

	@Override
	protected CmdAbst getCmdNormal() {
		return new NtfyCmdClsChnl(seshName, chnlType, chnlName);
	}

	@Override
	protected String getXMLNormal() {
		return xml;
	}

	@Override
	protected String getActionName() {
		return "ClsChnl";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.cmd.CmdTestAbst#getCmdNormal(org.w3c.dom.Document)
	 */
	@Override
	protected CmdAbst getCmdNormal(Document doc) throws CumExcpIllegalCmdDoc {

		return new NtfyCmdClsChnl(doc);
	}

}
