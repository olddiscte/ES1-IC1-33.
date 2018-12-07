package programs;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;
import com.restfb.Version;

import interfaces.ResultsInterface;

public class FacebookProg {

	private String acessToken; 
	FacebookClient fbClient;
	User me;

	public FacebookProg() {
		//Token (Chave) de acesso (Tem de ser renovada de x em x tempo)
		acessToken = "EAAChfjQq2ZBkBAMOal5wsT30f1OCGzHNFcBENxqfTNn7ZC2NRIitlJEuPZCeyTKl1gHpIZAtLuZCpHnnMZBMX8NZCtjp6RpPPoA7xXMcJIWcqdnf5jNPYXa9M4WZBEUXTcfotwL4Wf9jYOHMdlf9cIU6OOYNQZA0UJ6lJPpIXe8xyO1ZCuZBQtE1ZALqgw0eAPpdVOrLLLB5Gn27aAZDZD";
		Version x = Version.LATEST;
		fbClient = new DefaultFacebookClient(acessToken, x );
		me = fbClient.fetchObject("me", User.class);
		System.out.println(me.getName());
	}
	
	//Metodo que procura os posts do Usr
	public void searchUserFeed() { 

		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);
		int counter = 0;
		StringBuilder texto = new StringBuilder();
		
		for(List<Post> page : result) {
			for(Post aPost : page) {
				texto.append("MESSAGE:  " + aPost.getMessage() + "\n ");
				texto.append("URL:  " + "fb.com/" + aPost.getId() + "\n ");
				System.out.println(aPost.getMessage());
				System.out.println("fb.com/" + aPost.getId());
				counter ++;
			}
		}
		
		System.out.println("Number of results:" + counter);
		String second = texto.toString();
		ResultsInterface results = new ResultsInterface(second);
		results.open();
		
	}
	
	//Metodo que faz post no facebook
	public void post(String message) {
		 fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", message));
	}
	
}
