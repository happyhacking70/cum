/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.res.impl.ResCmdClsSesh;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdJoinChnl;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRegChnl;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRegSesh;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdUnknowCmd;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.CumTestAbst;
import jp.happyhacking70.cum.presSvr.adptrLyr.discnHdlr.DiscnHdlrAbst;
import jp.happyhacking70.cum.presSvr.adptrLyr.discnHdlr.DiscnHdlrAud;
import jp.happyhacking70.cum.presSvr.adptrLyr.discnHdlr.DiscnHdlrPrestr;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNull;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr;

import org.javatuples.Pair;
import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrTest extends CumTestAbst {

	protected PresSvrAdptr adptr = new PresSvrAdptr(new SeshMgrPresSvr());

	/**
	 * @throws IOException
	 */
	public PresSvrAdptrTest() throws IOException {
		super();
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.adptrLyr.PresSvrAdptr#hndlCmd(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpXMLGenFailed
	 * @throws CumExcpIllegalCmdDoc
	 * @throws CumExcpIllegalCmdXML
	 */
	@Test
	public void testHndlCmdStringPrestrCmd() throws CumExcpSeshExists,
			CumExcpXMLGenFailed, CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"ClsSesh\" SESH=\"testSession\" TYPE=\"REQ\"/></CUM>";
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);

		PresSvrAdptr adptr = new PresSvrAdptr(seshMgr);

		Pair<String, DiscnHdlrAbst> p = adptr.hndlCmd(xml);

		assertEquals(DiscnHdlrPrestr.class, p.getValue1().getClass());
		assertEquals(
				new ResCmdClsSesh(seshName, ResCmdClsSesh.RsltTypes.Clsed)
						.toXmlStr(),
				p.getValue0());
	}

	@Test
	public void testHndlCmdStringAudCmdCmd() throws CumExcpSeshExists,
			CumExcpXMLGenFailed, CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpSeshNotExist, CumExcpAudExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"JoinChnl\" AUD=\"testAudience\" CHNL=\"testChannel\" CHNLTYPE=\"testChannelType\" SESH=\"testSession\" TYPE=\"REQ\"/></CUM>";
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.regChnl(seshName, chnlType, chnlName, rscesArray);
		seshMgr.joinSesh(seshName, audName, senderForAudA);

		PresSvrAdptr adptr = new PresSvrAdptr(seshMgr);

		Pair<String, DiscnHdlrAbst> p = adptr.hndlCmd(xml);

		assertEquals(DiscnHdlrAud.class, p.getValue1().getClass());
		assertEquals(new ResCmdJoinChnl(seshName, chnlType, chnlName, audName,
				ResCmdJoinChnl.RsltTypes.Joined.name()).toXmlStr(),
				p.getValue0());
	}

	@Test(expected = CumExcpIllegalCmdDoc.class)
	public void testHndlCmdStringBrokenXML() throws CumExcpSeshExists,
			CumExcpXMLGenFailed, CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><A></A>";
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);

		PresSvrAdptr adptr = new PresSvrAdptr(seshMgr);
		adptr.hndlCmd(xml);

	}

	@Test
	public void testHndlCmdStringUnknownCmd() throws CumExcpSeshExists,
			CumExcpXMLGenFailed, CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc {
		senderForPrestr.clearQueue();
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"ClsSesh\" RSLT=\"Clsed\" SESH=\"testSession\" TYPE=\"RES\"/></CUM>";
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);

		PresSvrAdptr adptr = new PresSvrAdptr(seshMgr);
		Pair<String, DiscnHdlrAbst> p = adptr.hndlCmd(xml);
		assertEquals(new ResCmdUnknowCmd().toXmlStr(), p.getValue0());
		assertNull(p.getValue1());

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.adptrLyr.PresSvrAdptr#hndlCmd(java.lang.String, jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf)}
	 * .
	 * 
	 * @throws CumExcpXMLGenFailed
	 * @throws CumExcpIllegalCmdDoc
	 * @throws CumExcpIllegalCmdXML
	 */
	@Test
	public void testHndlCmdStringCmdSenderIntf() throws CumExcpXMLGenFailed,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"RegSesh\" SESH=\"testSession\" TYPE=\"REQ\"/></CUM>";
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();

		PresSvrAdptr adptr = new PresSvrAdptr(seshMgr);
		Pair<String, DiscnHdlrAbst> p = adptr.hndlCmd(xml, senderForPrestr);
		assertNull(senderForPrestr.pollCmd());
		assertEquals(DiscnHdlrPrestr.class, p.getValue1().getClass());
		assertEquals(
				new ResCmdRegSesh(seshName, ResCmdRegSesh.RsltTypes.Reged)
						.toXmlStr(),
				p.getValue0());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.adptrLyr.PresSvrAdptr#hndlCmd(java.lang.String, java.util.ArrayList)}
	 * .
	 * 
	 * @throws CumExcpXMLGenFailed
	 * @throws CumExcpSeshExists
	 * @throws CumExcpIllegalCmdDoc
	 * @throws CumExcpIllegalCmdXML
	 */
	@Test
	public void testHndlCmdStringArrayListOfChnlRscIntf()
			throws CumExcpXMLGenFailed, CumExcpSeshExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CUM><CMD ACTION=\"RegChnl\" CHNL=\"testChannel\" CHNLTYPE=\"testChannelType\" SESH=\"testSession\" TYPE=\"REQ\"><RSC NAME=\"a\"/><RSC NAME=\"b\"/></CMD></CUM>";
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, senderForPrestr);

		PresSvrAdptr adptr = new PresSvrAdptr(seshMgr);
		Pair<String, DiscnHdlrAbst> p = adptr.hndlCmd(xml, rscesArray);

		assertEquals(new ResCmdRegChnl(seshName, chnlType, chnlName,
				rscesArray, ResCmdRegChnl.RsltTypes.Reged).toXmlStr(),
				p.getValue0());
		assertEquals(DiscnHdlrPrestr.class, p.getValue1().getClass());
	}
}
