/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_lvChnl_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		AudSesh sesh = getSesh();
		sesh.lvChnl(chnlNameA);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoJoining();
		sesh.lvChnl(chnlNameA);
	}

	@Test
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoJoined();
		try {
			sesh.joinChnl(chnlNameA);
			sesh.chnlJoined(chnlNameA);
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("");
		}

		sesh.lvChnl(chnlNameA);

		assertEquals(chnlType, adptr.getChnlTypeToLvChnl());
		assertEquals(chnlNameA, adptr.getChnlNameToLevChnl());
		assertEquals(seshName, adptr.getSeshNameToLvChnl());
	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TestJoined_NOCHNL() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoJoined();
		try {
			sesh.joinChnl(chnlNameA);
			sesh.chnlJoined(chnlNameA);
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("");
		}

		sesh.lvChnl("XXX");

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoLving();
		sesh.lvChnl(chnlNameA);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoClsed();
		sesh.lvChnl(chnlNameA);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		AudSesh sesh = gotoDscned();
		sesh.lvChnl(chnlNameA);
	}
}
