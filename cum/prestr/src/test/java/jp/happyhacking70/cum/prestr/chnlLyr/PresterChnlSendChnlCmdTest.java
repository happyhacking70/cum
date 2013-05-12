/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresterChnlSendChnlCmdTest extends PrestrChnlTestAbst {
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_sendChnlCmd_REGING() throws CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus {
		PrestrChnl chnl = getChnl();
		chnl.sendChnlCmd(actionName, params);

	}

	@Test
	public void TEST_sendChnlCmd_REGED() throws CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus, TestExcp {
		PrestrChnl chnl = gotoReged();
		chnl.sendChnlCmd(actionName, params);
		assertEquals(actionName, sesh.getActionName());
		assertEquals(params, sesh.getParams());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_sendChnlCmd_CLSING() throws CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus, TestExcp {
		PrestrChnl chnl = gotoClsing();
		chnl.sendChnlCmd(actionName, params);

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_sendChnlCmd_CLSED() throws CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus, TestExcp {
		PrestrChnl chnl = gotoClsed();
		chnl.sendChnlCmd(actionName, params);
		fail("");
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_sendChnlCmd_DISCNED() throws CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus, TestExcp {
		PrestrChnl chnl = gotoDscned();
		chnl.sendChnlCmd(actionName, params);

	}

}
