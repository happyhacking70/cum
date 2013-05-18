/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking.cum.aud.excp.CumExcpIgnoreChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudChnl_chnlCmdRcved_Test extends AudChnlTestAbst {

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_init() throws CumExcpIllegalChnlStatus,
			CumExcpIgnoreChnlStatus {
		AudChnl chnl = getChnl();
		chnl.chnlCmdRcved(actionName, params);
	}

	@Test
	public void test_joining() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIgnoreChnlStatus {
		AudChnl chnl = goto_joining();
		assertNull(chnlView.getActionName());
		assertNull(chnlView.getParams());
		chnl.chnlCmdRcved(actionName, params);
		assertEquals(actionName, chnlView.getActionName());
		assertEquals(params, chnlView.getParams());
	}

	@Test
	public void test_joined() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIgnoreChnlStatus {
		AudChnl chnl = goto_joining();
		assertNull(chnlView.getActionName());
		assertNull(chnlView.getParams());
		chnl.chnlCmdRcved(actionName, params);
		assertEquals(actionName, chnlView.getActionName());
		assertEquals(params, chnlView.getParams());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_rjcting() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIgnoreChnlStatus {
		AudChnl chnl = goto_rjcting();
		chnl.chnlCmdRcved(actionName, params);
	}

	@Test
	public void test_lving() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIgnoreChnlStatus {
		AudChnl chnl = goto_lving();
		chnl.chnlCmdRcved(actionName, params);
		assertNull(chnlView.getActionName());
		assertNull(chnlView.getParams());
	}

	@Test
	public void test_clsed() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIgnoreChnlStatus {
		AudChnl chnl = goto_clsed();
		chnl.chnlCmdRcved(actionName, params);
		assertNull(chnlView.getActionName());
		assertNull(chnlView.getParams());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_dscned() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIgnoreChnlStatus {
		AudChnl chnl = goto_dscned();
		chnl.chnlCmdRcved(actionName, params);
	}

}
