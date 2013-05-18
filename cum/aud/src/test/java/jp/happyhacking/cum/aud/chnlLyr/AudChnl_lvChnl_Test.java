/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudChnl_lvChnl_Test extends AudChnlTestAbst {

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_init() throws CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		AudChnl chnl = getChnl();
		chnl.lvChnl();

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_joining() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		AudChnl chnl = goto_joining();
		chnl.lvChnl();
	}

	@Test
	public void test_joined() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		AudChnl chnl = goto_joined();
		chnl.lvChnl();
		assertEquals(AudChnl.Status.lving, chnl.chnlStatus);
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_rjcting() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		AudChnl chnl = goto_rjcting();
		chnl.lvChnl();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_lving() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		AudChnl chnl = goto_lving();

		chnl.lvChnl();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_clsed() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		AudChnl chnl = goto_clsed();
		chnl.lvChnl();

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_dscned() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		AudChnl chnl = goto_dscned();
		chnl.lvChnl();
	}
}
