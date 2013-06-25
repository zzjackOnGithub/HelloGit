package com.zzjack.io;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
public class InitTest {
	public static Collection<String> fill(Collection<String> collection){ 
		collection.add("fuck");
		collection.add("shit");
		collection.add("damn");
		collection.add("jesues");
		return collection;
	}
	
	public static void main(String[] args) {
		
		Runtime rt = Runtime.getRuntime();
		
		String[] command1=new String[]{"cmd","cd","C://Program Files//Windows Media Player//wmplayer"};
		String mycommand = "cmd /k notepad";
        String command = "taskkill /F /IM notepad";      
        try  
        {  
          System.out.println(rt.exec(mycommand));//返回一个进程  
          
        Thread.currentThread().sleep(10000);
          rt.exec(command);  
          System.out.println("success closed");  
        }  
        catch (Exception e)  
        {  
          e.printStackTrace();  
        }  
		
//		Sub sb = new Sub();
//		int [] a = new int[]{2,3,4};
////		void[] va = new void[]{};
//		Integer[] Inta = new Integer[]{1,2,3};
//		String[] s = new String[]{"1","2","s","b","f"};
//		//Why this do not work?
//		//List<Integer> intList = Arrays.asList(a);
//		
////		List<Integer> intList = Arrays.<Integer>asList(a);
//		
//		TreeSet<String> tset = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
//		List<Integer> Intl = Arrays.asList(Inta);
////		Intl.addAll(Intl.size(), Arrays.asList(Inta));
//		System.out.println(Intl);
//		List<String> strl = Arrays.asList(s);
//		ListIterator<String> listit = strl.listIterator();
//		System.out.println(listit.previousIndex()+" "+listit.nextIndex());
//		
//		LinkedList<String> ls = new LinkedList<String>(strl);
//		System.out.println("ls.getFirst"+ls.getFirst());
////		System.out.println("ls.element"+ls.);
//		Map<String , Integer> map = new HashMap<String, Integer>();
//		Collections.
		int [] a = new int[1000000];//约3.8M
		Integer [] result = new Integer[1000000];
		Arrays.fill(a, 0);
		Random rand = new Random(System.currentTimeMillis());
		int collision = 0;
		long time = System.currentTimeMillis();
		for(int i = 0, j = 0;j<1000000;i++){
			int r = rand.nextInt(1000000);
			if(a[r]!=0){
				collision++;
			}else{
				result[j++] = r;
			}
			a[r]++;			
		}
		System.out.println("usedtime: "+ (System.currentTimeMillis()-time));
		System.out.println(collision);
		Arrays.sort(a);
		System.out.println(a[a.length-1]);
//		System.out.println(Arrays.asList(result));

		//Set 
		Set<Integer> resultSet = new LinkedHashSet<Integer>();
		time = System.currentTimeMillis();
		for(;resultSet.size()<1000000;){
			resultSet.add(rand.nextInt(1000000));
		}
		System.out.println("Set usedtime: "+ (System.currentTimeMillis()-time));		
		//shuffle版本。
		Arrays.fill(result, 0);
		time = System.currentTimeMillis();
		for(int i = 0;i<result.length;i++){
			result[i] = i;
		}
		Collections.shuffle(Arrays.asList(result));
		System.out.println("Shuffle usedtime: "+ (System.currentTimeMillis()-time));		
		//----avoid collision
		time = System.currentTimeMillis();
		for(int i = 0;i<result.length;i++){
			result[i] = i;
		}
		for(int i = 0;i<result.length;i++){
			int r = rand.nextInt(result.length-i);
			//switch result[r] and result[result.length-i-1];
			int temp = result[r];
			result[r] = result[result.length-i-1];
			result[result.length-i-1] = temp;
		}
		System.out.println("avoid collision usedtime: "+ (System.currentTimeMillis()-time));	
		
	} 
}


class Temp{
	public Temp(int i){
		System.out.println("temp"+i);
	}
}
class Base{
	private String s= new String("test");
	private Temp t = new Temp(1);
	private static int count = 0;
	public int id;
	static{
		System.out.println("base static arrea");
	}
	public Base(){
		this.id = count++;
		System.out.println("base:"+ id +s);
		
	}
}

class Sub extends Base{
	private String ss = new String("sub");
	private Temp t = new Temp(2);

	private Base bs = new Base();
	
	{
		System.out.println("实例初始化");
	}
	static{
		System.out.println("sub static arrea");
	}
	public Sub(){
		System.out.println("sub:"+ss+" "+bs.id);
	}
}