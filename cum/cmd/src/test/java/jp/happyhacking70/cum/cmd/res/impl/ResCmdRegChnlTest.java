/**
 * 
 */
package jp.happyhacking70.cum.cmd.res.impl;

import java.io.IOException;
import java.util.ArrayList;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.ResCmdChnlTestAbst;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscImg;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.junit.BeforeClass;
import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class ResCmdRegChnlTest extends ResCmdChnlTestAbst {

	/**
	 * @throws IOException
	 */
	public ResCmdRegChnlTest() throws IOException {
		super();
	}

	static final public String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"RegChnl\" CHNL=\"testChannel\" CHNLTYPE=\"testChannelType\" RSLT=\"Reged\" SESH=\"testSession\" TYPE=\"RES\"><RSC NAME=\"a\"/><RSC NAME=\"b\"/></CMD></CUM>";
	static final ResCmdRegChnl.RsltTypes rslt = ResCmdRegChnl.RsltTypes.Reged;

	@Override
	protected String getXMLNormal() {
		return xml;
	}

	@Override
	protected String getActionName() {
		return "RegChnl";
	}

	@BeforeClass
	public static final void setBeforeClass() {
		System.out.println(xml);
	}

	@Override
	protected CmdAbst getCmdNormal() {
		ResCmdRegChnl cmd = new ResCmdRegChnl(seshName, chnlType, chnlName,
				rscesArray, rslt);

		return cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.cmd.CmdTestAbst#getCmdNormal(org.w3c.dom.Document)
	 */
	@Override
	protected CmdAbst getCmdNormal(Document doc) throws CumExcpIllegalCmdDoc {
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		rsces.add(new ChnlRscImg("a", null));
		rsces.add(new ChnlRscImg("b", null));
		ResCmdRegChnl cmd = new ResCmdRegChnl(doc);
		return cmd;
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
