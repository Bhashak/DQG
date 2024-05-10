package Response;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.testng.annotations.Test;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class DQG_Response {

	public int login_status;
	public int ConnectionList_status;
	public String login_responsebody;
	public String ConnectionList_responsebody;
	@Test
	public void Login_and_ConnectionList() throws EmailException
	{
		HttpResponse<String> Login_response = Unirest.post("https://kairostech.ai/DQG-AuthNZ/api/Login/Userlogin")
				  .header("Content-Type", "application/json")
				  .body("{\r\n"
				  		+ "    \"EncryptedData\": \"py_AauTREySmbudklrv2sbLRl4e_2xHui1TbvoLsB2VJbCmIirkhK6sAIy0VhcnO0dZUbhVgapcgRlnmbvoAbJbl7AUzxK9opfLT6fDPz3k=\"\r\n"
				  		+ "}")
				  .asString();
				
				int login_status=Login_response.getStatus();
				//Headers responsebody=response.getHeaders();
				String login_responsebody=Login_response.getBody();
				
				System.out.println("Status = "+login_status);
				System.out.println("Body = "+login_responsebody);
				
				 HttpResponse<String> Connection_List_response = Unirest.post("https://kairostech.ai/DQG-Server/api/data-source/list")
			                .header("Content-Type", "application/json")
			                .body("{\"key\": \"\"}")
			                .asString();
						int ConnectionList_status=Connection_List_response.getStatus();
						System.out.println("Connections List Status = "+Connection_List_response.getStatus());
						if(login_status != 200 && ConnectionList_status != 200 )
						{
						 HtmlEmail email = new HtmlEmail();
			                email.setHostName("smtp.gmail.com");
			                email.setSmtpPort(587);
			                email.setAuthenticator(new DefaultAuthenticator("kmbb4578@gmail.com", "ntto lwsv rfwa plhk"));
			                email.setSSLOnConnect(true);
		
			                email.setFrom("kmbb4578@gmail.com");
			                email.setSubject("DQG Server is not working");
			                email.setContent("Hi Team.<br>DQG Working Status:<br>" +"Login status : "+login_status + "<br>Connections List Status: " +ConnectionList_status , "text/html");
			                email.addCc("lakshminarayana.g@kairostech.com");
			                email.addCc("bhasha.k@kairostech.com");
			     	      	email.addTo("ravikumar.p@kairostech.com");
//			                
			                email.send();
						}
	}
}
