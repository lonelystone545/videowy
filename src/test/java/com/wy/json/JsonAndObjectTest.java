package com.wy.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonAndObjectTest {

	private User user = new User();
	@Before
	public void init() {
        user.setId("01");  
        user.setName("张三");  
        user.setAge(33);  
        user.setPay(6666.88);  
        user.setValid(true);  
        user.setOne('E');  
        user.setBirthday(new Date(20l*366*24*3600*1000)); //1990年  
          
        Link link = new Link();  
        link.setAddress("河南省济源市");  
        link.setPhone("13899995555");  
        link.setQq("123456");  
        user.setLink(link);  
          
        Map map=new HashMap();  
        map.put("aa", "this is aa");  
        map.put("bb", "this is bb");  
        map.put("cc", "this is cc");  
        user.setMap(map);  
          
        List list=new ArrayList(){};  
        list.add("普洱");  
        list.add("大红袍");  
        user.setList(list);  
          
        Set set=new HashSet();  
        set.add("篮球");  
        set.add("足球");  
        set.add("乒乓球");  
        user.setSet(set);  
	}
	
	@Test
	public void test1() {
		
        ObjectMapper objectMapper = new ObjectMapper();
        //对象转换为json
        String json = null;
		try {
			json = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //list和set都转换为数组了
        System.out.println("json: "+json);

        //json转换为map
        Map<Object, Object> m = null;
		try {
			m = objectMapper.readValue(json, Map.class);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for(Map.Entry<Object,Object> entry : m.entrySet()) {
        	System.out.println(entry.getKey()+":"+entry.getValue());
        }
        
        System.out.println("----------------------");
        //map转换为json
        try {
			json = objectMapper.writeValueAsString(m);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("json: "+json);
        
        System.out.println("-------------------------------");
        //json转换为User对象
        try {
        	//这里是内部类，出现问题了
			user =objectMapper.readValue(json, User.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("============================");
			System.out.println(user);
		}
	}
	
	class Link {
		private String phone; //移动电话  
	    private String address; //地址  
	    private String qq; //QQ  
	      
	    public String getPhone()  
	    {  
	        return phone;  
	    }  
	    public void setPhone(String phone)  
	    {  
	        this.phone = phone;  
	    }  
	    public String getAddress()  
	    {  
	        return address;  
	    }  
	    public void setAddress(String address)  
	    {  
	        this.address = address;  
	    }  
	    public String getQq()  
	    {  
	        return qq;  
	    }  
	    public void setQq(String qq)  
	    {  
	        this.qq = qq;  
	    }  
	}
	
	class User {
		private String id; //标识  
	    private String name;    //姓名  
	    private int age;    //年龄  
	    private double pay; //工资   
	    private boolean valid;  //是否有效  
	    private char one; //一个字符  
	    private Date birthday;  //生日  
	      
//	    @JsonIgnore
	    private Link link; //联系方式，自定义  
	      
	    private Map map; //测试用  
	    private List list; //测试用  
	    private Set set; //测试用  
	    public String getId()  
	    {  
	        return id;  
	    }  
	    public void setId(String id)  
	    {  
	        this.id = id;  
	    }  
	    public String getName()  
	    {  
	        return name;  
	    }  
	    public void setName(String name)  
	    {  
	        this.name = name;  
	    }  
	    public int getAge()  
	    {  
	        return age;  
	    }  
	    public void setAge(int age)  
	    {  
	        this.age = age;  
	    }  
	    public double getPay()  
	    {  
	        return pay;  
	    }  
	    public void setPay(double pay)  
	    {  
	        this.pay = pay;  
	    }  
	    public boolean isValid()  
	    {  
	        return valid;  
	    }  
	    public void setValid(boolean valid)  
	    {  
	        this.valid = valid;  
	    }  
	    public char getOne()  
	    {  
	        return one;  
	    }  
	    public void setOne(char one)  
	    {  
	        this.one = one;  
	    }  
	    public Date getBirthday()  
	    {  
	        return birthday;  
	    }  
	    public void setBirthday(Date birthday)  
	    {  
	        this.birthday = birthday;  
	    }  
	    public Link getLink()  
	    {  
	        return link;  
	    }  
	    public void setLink(Link link)  
	    {  
	        this.link = link;  
	    }  
	    public Map getMap()  
	    {  
	        return map;  
	    }  
	    public void setMap(Map map)  
	    {  
	        this.map = map;  
	    }  
	    public List getList()  
	    {  
	        return list;  
	    }  
	    public void setList(List list)  
	    {  
	        this.list = list;  
	    }  
	    public Set getSet()  
	    {  
	        return set;  
	    }  
	    public void setSet(Set set)  
	    {  
	        this.set = set;  
	    }
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", age=" + age + ", pay=" + pay + ", valid=" + valid + ", one="
					+ one + ", birthday=" + birthday + ", link=" + link + ", map=" + map + ", list=" + list + ", set="
					+ set + "]";
		}  
	}

}

