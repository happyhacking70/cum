/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshSendChnlCmdTest extends PrestrSeshAbstTest {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_sendChnlCmd_INIT() throws CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = getSesh();
		sesh.sendChnlCmd(chnlNameA, actionName, params);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_sendChnlCmd_REGING() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReging();
		sesh.sendChnlCmd(chnlNameA, actionName, params);
	}

	@Test
	public void TEST_sendChnlCmd_REGED() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReged();
		sesh.sendChnlCmd(chnlNameA, actionName, params);
		assertEquals(chnlNameA, adptr.getChnlNameToSendCmd());
	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TEST_sendChnlCmd_REGED_NOCHNL() throws TestExcp,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReged();
		sesh.sendChnlCmd(chnlNameC, actionName, params);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_sendChnlCmd_CLSING() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoClsing();
		sesh.sendChnlCmd(chnlNameA, actionName, params);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_sendChnlCmd_CLSED() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.sendChnlCmd(chnlNameA, actionName, params);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_sendChnlCmd_DISCNED() throws TestExcp,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoDiscned();
		sesh.sendChnlCmd(chnlNameA, actionName, params);
	}

}
