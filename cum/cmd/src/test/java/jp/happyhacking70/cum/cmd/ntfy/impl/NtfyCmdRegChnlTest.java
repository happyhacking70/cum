/**
 * 
 */
package jp.happyhacking70.cum.cmd.ntfy.impl;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.NtfyCmdChnlTestAbst;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscImg;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.junit.BeforeClass;
import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class NtfyCmdRegChnlTest extends NtfyCmdChnlTestAbst {
	/**
	 * @throws IOException
	 */
	public NtfyCmdRegChnlTest() throws IOException {
		super();
	}

	static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"RegChnl\" CHNL=\"testChannel\" CHNLTYPE=\"testChannelType\" SESH=\"testSession\" TYPE=\"NTFY\"><RSC NAME=\"a\"/><RSC NAME=\"b\"/></CMD></CUM>";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(xml);
	}

	@Override
	protected CmdAbst getCmdNormal() {

		NtfyCmdRegChnl cmd = new NtfyCmdRegChnl(seshName, chnlType, chnlName);
		cmd.addRscData(new ChnlRscImg("a", null));
		cmd.addRscData(new ChnlRscImg("b", null));
		return cmd;
	}

	@Override
	protected String getXMLNormal() {
		return xml;
	}

	@Override
	protected String getActionName() {
		return "RegChnl";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.cmd.CmdTestAbst#getCmdNormal(org.w3c.dom.Document)
	 */
	@Override
	protected CmdAbst getCmdNormal(Document doc) throws CumExcpIllegalCmdDoc {

		return new NtfyCmdRegChnl(doc);
	}

}
