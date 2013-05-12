/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresterChnlAudDscnedTest extends PrestrChnlTestAbst {
	@Test
	public void TEST_audDiscned_REGING() throws CumExcpAudExists,
			CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus,
			CumExcpAudNotExist {
		PrestrChnl chnl = getChnl();
		chnl.audJoinedChnl(audName);
		chnl.audDiscned(audName);
		assertEquals(audName, chnlView.getAudDiscned());
	}

	@Test(expected = CumExcpAudNotExist.class)
	public void TEST_audDiscned_REGING_NOAUD() throws TestExcp,
			CumExcpIgnoreChnlStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.audJoinedChnl(audName);
		} catch (CumExcpAudExists e) {
			throw new TestExcp("audJoinedChnl");
		} catch (CumExcpIllegalChnlStatus e) {
			throw new TestExcp("audJoinedChnl");
		}
		chnl.audDiscned(audNameB);
	}

	@Test
	public void TEST_audDiscned_REGED() throws TestExcp, CumExcpAudExists,
			CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus,
			CumExcpAudNotExist {
		PrestrChnl chnl = gotoReged();
		chnl.audDiscned(audName);
		assertEquals(audName, chnlView.getAudDiscned());
	}

	@Test
	public void TEST_audDiscned_CLSING() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudNotExist {
		PrestrChnl chnl = gotoClsing();

		chnl.audDiscned(audName);
		assertNull(chnlView.getAudDiscned());
	}

	@Test
	public void TEST_audDiscned_CLSED() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudNotExist {
		PrestrChnl chnl = gotoClsed();
		chnl.audDiscned(audName);
		assertNull(chnlView.getAudDiscned());
	}

	@Test
	public void TEST_audDiscned_DISCNED() throws CumExcpIllegalChnlStatus,
			CumExcpAudNotExist, TestExcp {
		PrestrChnl chnl = gotoDscned();
		chnl.audDiscned(audName);
		assertNull(chnlView.getAudDiscned());
	}

}
