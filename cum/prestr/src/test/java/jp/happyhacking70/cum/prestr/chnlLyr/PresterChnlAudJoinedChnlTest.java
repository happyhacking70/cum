/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresterChnlAudJoinedChnlTest extends PrestrChnlTestAbst {
	@Test
	public void TEST_audJoinedChnl_REGING() throws CumExcpIllegalChnlStatus,
			CumExcpAudExists {
		PrestrChnl chnl = getChnl();

		chnl.audJoinedChnl(audName);
		assertEquals(audName, chnlView.getAudJoined());
	}

	@Test(expected = CumExcpAudExists.class)
	public void TEST_audJoinedChnl_REGING_AUDEXISTS() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudExists {
		PrestrChnl chnl = getChnl();

		try {
			chnl.audJoinedChnl(audName);
		} catch (CumExcpIllegalChnlStatus e) {
			throw new TestExcp("audJoinedChnl");
		} catch (CumExcpAudExists e) {
			throw new TestExcp("audJoinedChnl");
		}
		chnl.audJoinedChnl(audName);

	}

	@Test
	public void TEST_audJoinedChnl_REGED() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudExists {

		PrestrChnl chnl = gotoReged();

		chnl.audJoinedChnl(audNameB);
		assertEquals(audNameB, chnlView.getAudJoined());
	}

	@Test
	public void TEST_audJoinedChnl_CLSING() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudExists {
		PrestrChnl chnl = gotoClsing();

		chnl.audJoinedChnl(audNameB);
		assertEquals(audName, chnlView.getAudJoined());
	}

	@Test
	public void TEST_audJoinedChnl_CLSED() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudExists {
		PrestrChnl chnl = gotoClsed();

		chnl.audJoinedChnl(audNameB);
		assertEquals(audName, chnlView.getAudJoined());
	}

	@Test
	public void TEST_audJoinedChnl_DISCNED() throws CumExcpIllegalChnlStatus,
			CumExcpAudExists, TestExcp {
		PrestrChnl chnl = gotoDscned();

		chnl.audJoinedChnl(audNameB);
		assertNull(chnlView.getAudJoined());
	}

}
