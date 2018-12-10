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


/**
 * @author Pedro Santos/Pedro Brites
 *
 */
public class FacebookProg {

	private String acessToken; 
	FacebookClient fbClient;
	User me;

	/**
	 * Construtor
	 */
	public FacebookProg() {
		//Token (Chave) de acesso (Tem de ser renovada de x em x tempo)
		acessToken = "EAAChfjQq2ZBkBAD0l2spRvYZBLsy8Gynj2k2xXUdco84QJD4PIZAfz5NtIOhZB5VrLNyH8oFxJkq6kb9GLrfvRLtXU1clzpmgaUMsgtwsZCiLGyx8KDby8AG45JCvsZAsIhjlMXrLZAjLVlFfJ2ZCMkpMhDJDrfOi5N9NpdTiQZC909MQqnk7UKcaMI668zFVIQ8TFUnvRCY0CAZDZD";
		Version x = Version.LATEST;
		fbClient = new DefaultFacebookClient(acessToken, x );
		me = fbClient.fetchObject("me", User.class);
		System.out.println(me.getName());
	}

	/**
	 * procura os posts mais recentes do user
	 */
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

	/**
	 * Publica a mensagem passada como argumento no mural do facebook do user (n/ funciona devido a API's)
	 * @param message
	 */
	public void post(String message) {
		fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", message));
	}

}
