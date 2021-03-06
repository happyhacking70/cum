/**
 * 
 */
package jp.happyhacking70.cum.cmd.req.impl;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.ReqCmdSeshAudTestAbst;
import jp.happyhacking70.cum.cmd.req.ReqCmdFromAudIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class ReqCmdLvSeshTest extends ReqCmdSeshAudTestAbst {
	/**
	 * @throws IOException
	 */
	public ReqCmdLvSeshTest() throws IOException {
		super();
	}

	static final public String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"LvSesh\" AUD=\"testAudience\" SESH=\"testSession\" TYPE=\"REQ\"/></CUM>";

	@Override
	protected CmdAbst getCmdNormal() {
		return new ReqCmdLvSesh(seshName, audName);
	}

	@Override
	protected String getXMLNormal() {
		return xml;
	}

	@Override
	protected String getActionName() {
		return "LvSesh";
	}

	@BeforeClass
	public static final void setBeforeClass() {
		System.out.println(xml);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.cmd.CmdTestAbst#getCmdNormal(org.w3c.dom.Document)
	 */
	@Override
	protected CmdAbst getCmdNormal(Document doc) throws CumExcpIllegalCmdDoc {
		return new ReqCmdLvSesh(doc);
	}

	@Test
	public void checkWhereFrom() {
		CmdAbst cmd = this.getCmdNormal();
		assertTrue(cmd instanceof ReqCmdFromAudIntf);
	}
}
