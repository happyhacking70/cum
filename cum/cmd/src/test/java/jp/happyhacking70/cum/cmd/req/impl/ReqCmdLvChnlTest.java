/**
 * 
 */
package jp.happyhacking70.cum.cmd.req.impl;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.ReqCmdChnlAudTestAbst;
import jp.happyhacking70.cum.cmd.req.ReqCmdFromAudIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class ReqCmdLvChnlTest extends ReqCmdChnlAudTestAbst {
	/**
	 * @throws IOException
	 */
	public ReqCmdLvChnlTest() throws IOException {
		super();
	}

	static final public String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"LvChnl\" AUD=\"testAudience\" CHNL=\"testChannel\" CHNLTYPE=\"testChannelType\" SESH=\"testSession\" TYPE=\"REQ\"/></CUM>";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(xml);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.cmd.CmdTestAbst#getCmdNormal()
	 */
	@Override
	protected CmdAbst getCmdNormal() {
		return new ReqCmdLvChnl(seshName, chnlType, chnlName, audName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.cmd.CmdTestAbst#getCmdNormal(org.w3c.dom.Document)
	 */
	@Override
	protected CmdAbst getCmdNormal(Document doc) throws CumExcpIllegalCmdDoc {
		return new ReqCmdLvChnl(doc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.cmd.CmdTestAbst#getXMLNormal()
	 */
	@Override
	protected String getXMLNormal() {
		return xml;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.cmd.CmdTestAbst#getActionName()
	 */
	@Override
	protected String getActionName() {
		return "LvChnl";
	}

	@Test
	public void checkWhereFrom() {
		CmdAbst cmd = this.getCmdNormal();
		assertTrue(cmd instanceof ReqCmdFromAudIntf);
	}

}
