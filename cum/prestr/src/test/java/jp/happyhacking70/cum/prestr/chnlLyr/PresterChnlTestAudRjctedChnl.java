/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresterChnlTestAudRjctedChnl extends PrestrChnlTestAbst {
	@Test
	public void TEST_audRjctedChnl_REGING() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudNotExist {
		PrestrChnl chnl = getChnl();
		try {
			chnl.audJoinedChnl(audName);
		} catch (CumExcpIllegalChnlStatus e) {
			throw new TestExcp("audJoinedChnl");
		} catch (CumExcpAudExists e) {
			throw new TestExcp("audJoinedChnl");
		}
		chnl.audRjctedChnl(audName);
		assertEquals(audName, chnlView.getAudRjcted());
	}

	@Test(expected = CumExcpAudNotExist.class)
	public void TEST_audRjctedChnl_REGING_NOAUD() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudNotExist {
		PrestrChnl chnl = getChnl();

		chnl.audRjctedChnl(audName);
	}

	@Test
	public void TEST_audRjctedChnl_REGED() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudNotExist {
		PrestrChnl chnl = gotoReged();

		chnl.audRjctedChnl(audName);
		assertEquals(audName, chnlView.getAudRjcted());
	}

	@Test
	public void TEST_audRjctedChnl_CLSING() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudNotExist {
		PrestrChnl chnl = gotoClsing();

		chnl.audRjctedChnl(audName);
		assertNull(chnlView.getAudRjcted());
	}

	@Test
	public void TEST_audRjctedChnl_CLSED() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudNotExist {
		PrestrChnl chnl = gotoClsed();

		chnl.audRjctedChnl(audName);
		assertNull(chnlView.getAudRjcted());
	}

	@Test
	public void TEST_audRjctedChnl_DISCNED() throws CumExcpIllegalChnlStatus,
			CumExcpAudNotExist {
		PrestrChnl chnl = gotoDscned();

		chnl.audRjctedChnl(audName);
		assertNull(chnlView.getAudRjcted());
	}

}
