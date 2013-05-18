/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdJoinChnl;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_chnlJoinFailed_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		AudSesh sesh = getSesh();
		sesh.chnlJoinFailed(chnlNameA, rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoJoining();
		sesh.chnlJoinFailed(chnlNameA, rslt);
	}

	@Test
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoJoined();
		try {
			sesh.joinChnl(chnlNameA);
		} catch (CumExcpIllegalSeshStatus e) {
			throw new TestExcp("joinChnl");
		} catch (CumExcpChnlNotExist e) {
			throw new TestExcp("joinChnl");
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("joinChnl");
		}
		sesh.chnlJoinFailed(chnlNameA, rslt);
		assertEquals(rslt, seshView.getRslt());
		assertEquals(chnlNameA, seshView.getChnlNameFailed());
	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TestJoined_NOCHNL() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoJoined();
		try {
			sesh.joinChnl(chnlNameA);
		} catch (CumExcpIllegalSeshStatus e) {
			throw new TestExcp("joinChnl");
		} catch (CumExcpChnlNotExist e) {
			throw new TestExcp("joinChnl");
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("joinChnl");
		}
		sesh.chnlJoinFailed("XXXX", rslt);
	}

	@Test
	public void TestJoined_NOCHNL_ONSVR() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		AudSesh sesh = gotoJoined();
		try {
			sesh.joinChnl(chnlNameA);
		} catch (CumExcpIllegalSeshStatus e) {
			throw new TestExcp("joinChnl");
		} catch (CumExcpChnlNotExist e) {
			throw new TestExcp("joinChnl");
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("joinChnl");
		}
		sesh.chnlJoinFailed(chnlNameA,
				ResCmdJoinChnl.RsltTypes.ChnlNotExist.name());
		assertEquals(ResCmdJoinChnl.RsltTypes.ChnlNotExist.name(),
				seshView.getRslt());
		assertEquals(chnlNameA, seshView.getChnlNameFailed());
		assertNull(sesh.chnls.get(chnlNameA));
	}

	@Test
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoLving();
		sesh.chnlJoinFailed(chnlNameA, rslt);
		assertNull(seshView.getChnlView().getRslt());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoClsed();
		sesh.chnlJoinFailed(chnlNameA, rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoDscned();
		sesh.chnlJoinFailed(chnlNameA, rslt);
	}

}
