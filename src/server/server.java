package server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class server {
	static String aname;
	static String address;
	
	public static void main(String[] args) {
		A a = new A();
		a.OpenApi();
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8765);
			System.out.println("서버가 준비되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {

				socket = serverSocket.accept();
				System.out.println("클라이언트가 연결되었습니다.");

				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();

				BufferedReader ain = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				String str = ain.readLine();
				String ar[] = str.split(" ");
// for(int i=0; i<ar.length; i++){
				System.out.println(str);

//aname = ar[0];
//address = ar[1];

//int su1 = Integer.parseInt(aname);
//int su2 = Integer.parseInt(address);
//int sum = 0;
//sum = su1 + su2;
//System.out.println(ar[0]);
//System.out.println(ar[1]);
//System.out.println(sum);
// }
//A A1 = new A();
//A1.insert();

			} catch (IOException e) {
				e.printStackTrace();

			} finally {
				try {
					if (socket != null)
						socket.close();

				} catch (Exception ignored) {

				}
				try {
					serverSocket.close();

				} catch (Exception ignored) {

				}
			}
		}
	}
}
class A {
	public String OpenApi() {
    BufferedReader br = null;
    String p = null;
    try{            
        String urlstr = "http://openapi.airkorea.or.kr/"
                + "openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst"
                + "?itemCode=PM10&dataGubun=HOUR"
                + "&pageNo=1&numOfRows=10"
                + "&ServiceKey=Zd1%2FidGpa4vGivD%2B3wCHr1%2Fw28%2FixVnoULPyDkdLIkc4uZZy2PZRwdREg47p2MnKub%2ForIm7%2BAM49bR15eqknA%3D%3D" + 
                "&ver=1.3&_returnType=json";
        URL url = new URL(urlstr);
        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
        urlconnection.setRequestMethod("GET");
        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
        String inputLine;
        StringBuffer stringBuffer = new StringBuffer();
        while((inputLine = br.readLine())!=null) {
        	stringBuffer.append(inputLine);
        }
        p = stringBuffer.toString();
        System.out.println(p);
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
    return p;
}
public void insert(String p) {
try {
Class.forName("org.gjt.mm.mysql.Driver");
} catch (ClassNotFoundException e1) {
System.err.println(e1);
}
 
Connection conn = null;
PreparedStatement pstmt = null;
 
String url = "jdbc:mysql://127.0.0.1:3306/cosmos";
String id = "root";
String pwd = "yuri587782!";
String query = null;
 
try {
conn = DriverManager.getConnection(url, id, pwd);
} catch (SQLException e1) {
System.err.println(e1);
}
 
while (true) {
String aname = server.aname;
String address = server.address;
 
query = "insert into bravo values(?,?)";
 
try {
pstmt = conn.prepareStatement(query);
pstmt.setString(1, aname);
pstmt.setString(2, address);
pstmt.executeUpdate();
pstmt.close();
System.exit(0);
} catch (SQLException e1) {
System.err.println(e1);
}
}
 
}
}
 

