package programs;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;

import interfaces.ResultsInterface;

public class FacebookProg {

	private String acessToken; 
	FacebookClient fbClient;
	User me;

	public FacebookProg() {
		//Token (Chave) de acesso (Tem de ser renovada de x em x tempo)
		acessToken = "EAAChfjQq2ZBkBAPYDiNuTn7rDue5ImZBZArO4ZCrH9hn5BY5HZB7TrIBqRjAHEcJ4rHIHDZAC4hiOWjOOy4DdTaSGz3U4IESvPSxTxK6wq6WVhBcpHN9TJcZCPI3T3ZBLTNedtYcNfD4czoR7wKg0nn6uwHUMwzOtwfpv4wwY4TkW8Eovq7k1SvrSR4YZBmc90QPfhC0gwRfI3TANeheBXmCC";
		fbClient = new DefaultFacebookClient(acessToken);
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
