/**
 * 
 */
package jp.happyhacking70.cum.cmd.res.impl;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.CmdAbst.CmdTypes;
import jp.happyhacking70.cum.cmd.CmdTestAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.junit.BeforeClass;
import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class ResCmdUnknownCmdTest extends CmdTestAbst {
	/**
	 * @throws IOException
	 */
	public ResCmdUnknownCmdTest() throws IOException {
		super();
	}

	static final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"UnknownCmd\" RSLT=\"Unknown\" TYPE=\"RES\"/></CUM>";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum.cmd.CmdTestAbst#getCmdNormal()
	 */
	@Override
	protected CmdAbst getCmdNormal() {
		return new ResCmdUnknowCmd();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.cmd.CmdTestAbst#getCmdNormal(org.w3c.dom.Document)
	 */
	@Override
	protected CmdAbst getCmdNormal(Document doc) throws CumExcpIllegalCmdDoc {
		return new ResCmdUnknowCmd(doc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum.cmd.CmdTestAbst#getXMLNormal()
	 */
	@Override
	protected String getXMLNormal() {
		return xml;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum.cmd.CmdTestAbst#getActionName()
	 */
	@Override
	protected String getActionName() {
		return "UnknownCmd";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum.cmd.CmdTestAbst#getCmdType()
	 */
	@Override
	protected CmdTypes getCmdType() {
		return CmdAbst.CmdTypes.RES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum.cmd.CmdTestAbst#myAddonToFromDocToInstance(jp.
	 * happyhacking70.cum.cmd.CmdAbst)
	 */
	@Override
	protected void myAddonToFromDocToInstance(CmdAbst cmd) {

	}

}
