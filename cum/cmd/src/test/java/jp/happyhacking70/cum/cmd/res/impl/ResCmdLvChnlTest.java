/**
 * 
 */
package jp.happyhacking70.cum.cmd.res.impl;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.ResCmdChnlAudTestAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.junit.BeforeClass;
import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class ResCmdLvChnlTest extends ResCmdChnlAudTestAbst {
	/**
	 * @throws IOException
	 */
	public ResCmdLvChnlTest() throws IOException {
		super();
	}

	static final public String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"LvChnl\" AUD=\"testAudience\" CHNL=\"testChannel\" CHNLTYPE=\"testChannelType\" RSLT=\"Left\" SESH=\"testSession\" TYPE=\"RES\"/></CUM>";
	static final ResCmdLvChnl.RsltTypes rslt = ResCmdLvChnl.RsltTypes.Left;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(xml);
	}

	@Override
	protected CmdAbst getCmdNormal() {
		return new ResCmdLvChnl(seshName, chnlType, chnlName, audName,
				rslt.name());
	}

	@Override
	protected String getXMLNormal() {
		return xml;
	}

	@Override
	protected String getActionName() {
		return "LvChnl";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.cmd.CmdTestAbst#getCmdNormal(org.w3c.dom.Document)
	 */
	@Override
	protected CmdAbst getCmdNormal(Document doc) throws CumExcpIllegalCmdDoc {
		return new ResCmdLvChnl(doc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum.cmd.ResCmdTestIntf#getNormalRslt()
	 */
	@Override
	public String getNormalRslt() {
		return rslt.name();

	}

}
