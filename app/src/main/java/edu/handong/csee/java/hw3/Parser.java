 package edu.handong.csee.java.hw3;
// key 값을 이용해서 데이터를 추출하는 class
public class Parser {
	private String keyString;
	private String kordata;
	private String[] smalldata;

	public Parser() {
		keyString = "";
		kordata ="";
	}
	
	public Parser(String key, String kordata) {
		keyString = key;
		this.kordata = kordata;
	}
	
	public void FindValue() {
		
		/* 처음 배열 array에서는 처음에는 \n로 json을 나눈다.
		 * 찾고자 하는 key의 줄에 {가 포함되어 있다면, bigdata method로 넘어간다.
		 * 그렇지 않다면,
		 * small data method로 넘어간다.
		 * 만약에 그 줄에 {를 포함하고 있지 않다면 :로 스트링을 나눈다.
		 * 찾고자 하는 key값이 이 배열에 포함되어 있다면 그 다음 배열의 값을 출력한다. print 함수에서 구현
		 */
		Smalldata();
	}
	
	private int Bigdata(int startIndex) {// data를 대괄호 기준으로 나눈다.
		int count1 = 0;
		int count2 = 0;
		int start = 0, end=0;
		String data = null;
		
		//시작 점 index의 값부터 시작한다. 
		//int i = startIndex;
		for(int i = startIndex;i<kordata.length();i++) {
			char ch = kordata.charAt(i);
			//System.out.println(ch);
			String str = Util.convertCharToString(ch);  
			if(str.equals("{")) {
				//count1를 1씩 증가한다. 
				count1++;
				//처음을 시작점으로 잡는다. 
				if(count1 == 1) start = i;
			
			}
			// }가 나올 경우 count2를 1씩 증가한다. 
			if(str.equals("}")) {
				count2++;
			}
			if(count1==count2&&count1!=0) {
				end =i;
				data = kordata.substring(start, end+1);
				break;
			} 
			//count1 과 2가 같아진다면 그 시점의 index를 end로 한다.
			// 그사이의 데이터를 추출한다.
			// print함수에 보내진다.
		}
		//만약 data안에 같은 이름의string이 있다면 
		PrintParse(data);
		//bigdata(data);
		return end;
	}
	
	private void Smalldata() {
		smalldata = kordata.split("\n|:");
		int size = smalldata.length;
		int start=0;
		int newStart=0;
		for(int i=0; i<size;i++) {
			smalldata[i]=smalldata[i].trim();
		}
		
		for(int i=0;i<size;i++) {
			if(smalldata[i].contains(keyString))
				//바로 옆에 {가 있으면 이 값을 묶어서 프린트 해야한다. 이 작업은 bigdata에서 한다. 
				if(smalldata[i+1].equals("{")) {
					start = kordata.indexOf(smalldata[i], start);
					newStart = Bigdata(start);
					start = newStart;
				}
				//smalldata안에는 parse한 단어가 들어가 있고, 이렇게 보내면 계속 같은 값을 리
				else
					PrintParse(smalldata[i+1]);
		}
		
	}
	
	public void PrintParse(String par) {
		System.out.println(par);
	}
}
