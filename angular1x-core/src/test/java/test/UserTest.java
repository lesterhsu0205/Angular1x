package test;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ycci.core.dao.ICommonDao;
import com.ycci.core.dao.IUserDao;
import com.ycci.core.model.CfgAuthUser;
import com.ycci.support.util.Pagination;
import com.ycci.support.viewModel.Option;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserTest {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private ICommonDao commonDao;
	
	@Value("${filePathProto}")
	private String filePathProto;
	
	@Test
	public void testLogin() throws Exception{
		CfgAuthUser bean = userDao.loadUserByUsername("yyy", false);
		System.out.println(bean);
	}
	
	@Test
	public void testOpt() throws Exception{
		List<Option> opts = userDao.queryRoleOpts();
		System.out.println(opts);
	}
	
	@Test
	public void testQuery() throws Exception{
		List<CfgAuthUser> users = userDao.queryUsers(new Pagination(1, 2));
		System.out.println(users);
	}
	
	@Test
	public void testCount() throws Exception{
		String count = userDao.queryUsersCount();
		System.out.println(count);
	}
	
	@Test
	public void testInser() throws Exception{
		CfgAuthUser user = new CfgAuthUser();
		user.setCreateUser(1L);
		user.setDepId(1l);
		user.setEmail("xxx");
		user.setName("xxx3");
		user.setRoleId(1L);
		user.setStatus(true);
		user.setUpdateUser(1L);
		user.setUserName("xxx");
		
		int value = 0;
		try {
			value = userDao.inserUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(value);
	}
	
	@Test
	public void testUpdate() throws Exception{
		CfgAuthUser user = new CfgAuthUser();
		user.setCreateUser(2L);
		user.setDepId(2l);
		user.setEmail("yyy1212");
		user.setName("yyy1212");
		user.setRoleId(2L);
		user.setStatus(false);
		user.setUpdateUser(2L);
//		user.setUserName("yyy");
		user.setId(3L);
		
		int val = 0;
		try {
			val = userDao.updateUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(val);
	}
	
	@Test
	public void testDelete() throws Exception{
		int val = 0;
		try {
			val = userDao.deleteUserById("6");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(val);
	}
	
	@Test
	public void testProperties() throws Exception{
		Resource resource = new ClassPathResource("properties/config.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		System.out.println(props.getProperty("filePathProto"));
	}
}
