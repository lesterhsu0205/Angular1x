package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ycci.core.dao.ICfgSysConfigDao;
import com.ycci.core.dao.impl.UserDaoImpl;
import com.ycci.core.model.CfgAuthUser;
import com.ycci.core.model.CfgSystemConfig;
import com.ycci.support.util.Pagination;

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
