/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_chnlRjctFailed_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		AudSesh sesh = getSesh();
		sesh.chnlRjctFailed(chnlNameA, rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoJoining();
		sesh.chnlRjctFailed(chnlNameA, rslt);
	}

	@Test
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoJoined();
		sesh.chnlRjctFailed(chnlNameA, rslt);
		assertEquals(chnlNameA, seshView.getChnlNameRjctFailed());
		assertEquals(rslt, seshView.getRsltChnlRjctFailed());
	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TestJoined_NOCHNL() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoJoined();
		sesh.chnlRjctFailed("XXX", rslt);
	}

	@Test
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoLving();
		sesh.chnlRjctFailed(chnlNameA, rslt);
		assertNull(seshView.getChnlNameRjctFailed());
		assertNull(seshView.getRsltChnlRjctFailed());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoClsed();
		sesh.chnlRjctFailed(chnlNameA, rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoDscned();
		sesh.chnlRjctFailed(chnlNameA, rslt);
	}
}
