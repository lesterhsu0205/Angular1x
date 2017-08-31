package test;

import com.lester.core.dao.ICfgSysConfigDao;
import com.lester.core.model.CfgSystemConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DarrenTest {

	@Autowired
	private ICfgSysConfigDao cfgSysConfigDao;
	
	@Test
	public void test1(){
		System.out.println("Start==============================");
		try {
			List<CfgSystemConfig> res = cfgSysConfigDao.queryByCodeCate("TOWN");
			System.out.println("~~~");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End==============================");
	}
	
	@Test
	public void test2(){
		System.out.println("Start==============================");
		try {
			Map<String, String> allItems = new HashMap<String, String>();
			allItems.put(null, "dsvp");
			String aa = allItems.get(null);
			
			System.out.println("~~~");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End==============================");
	}
	
	@Test
	public void test3(){
		System.out.println("Start==============================");
		try {
			System.out.println(DigestUtils.sha512Hex("123"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End==============================");
	}
}
