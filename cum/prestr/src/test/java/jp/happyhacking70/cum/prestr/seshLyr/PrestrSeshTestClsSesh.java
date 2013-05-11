/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshTestClsSesh extends PrestrSeshTestAbst {
	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsSesh_INIT() throws CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatuMulti {
		PrestrSesh sesh = getSesh();
		sesh.clsSesh();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsSesh_REGING() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatuMulti {
		PrestrSesh sesh = gotoReging();
		sesh.clsSesh();
	}

	@Test
	public void TEST_clsSesh_REGED() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatuMulti {
		PrestrSesh sesh = gotoReged();
		sesh.clsSesh();

		assertEquals(seshName, adptr.getSeshNameToCls());
		assertTrue(chnlViewA.isSeshClsing());
		assertTrue(chnlViewB.isSeshClsing());

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsSesh_CLSING() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatuMulti {
		PrestrSesh sesh = gotoClsing();
		sesh.clsSesh();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsSesh_CLSED() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatuMulti {
		PrestrSesh sesh = gotoClsed();
		sesh.clsSesh();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsSesh_DISCNED() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatuMulti {
		PrestrSesh sesh = gotoDiscned();
		sesh.clsSesh();
	}

}
