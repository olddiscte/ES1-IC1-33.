package programs;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import interfaces.ResultsInterface;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


/**
 * @author Pedro Santos/Pedro Brites
 *
 */
public class TwitterProg {

	twitter4j.Twitter twitter;

	/**
	 * Construtor	
	 */
	public TwitterProg() {
		ConfigurationBuilder cf = new ConfigurationBuilder();
		cf.setDebugEnabled(true);
		cf.setOAuthConsumerKey("n3n1uNxwuBqiVSqKll0Snibsm");
		cf.setOAuthConsumerSecret("MZftLdCTpcY8gouGf3ecfzBlxhV1igXoohvTH68TSW1ogyEZYp");
		cf.setOAuthAccessToken("1052615906860384256-AMmJqNFWIAk1LBQaJSIbc66hOWTYoK");
		cf.setOAuthAccessTokenSecret("yg0e5KH9S1eU83nr5WTdkpEmfq5hniCJMiVurdGxOFYnh");
		TwitterFactory tf = new TwitterFactory(cf.build());
		twitter= tf.getInstance();
	}

	/**
	 * Faz um tweet com a mensagem passada com argumento
	 * @param mensagem
	 * @throws TwitterException
	 */
	public void tweetar(String mensagem) throws TwitterException {

		Status status = twitter.updateStatus(mensagem);
		System.out.println("Successfully updated the status to [" + status.getText() + "].");

		ResultsInterface results = new ResultsInterface("Successfully updated the status to [" + status.getText() + "].");
		results.open();

	}

	/**
	 * Procura por tweets usando a palavra chave passada como argumento
	 * @param data
	 * @throws TwitterException
	 * @throws IOException
	 */
	public void procurar(String data) throws TwitterException, IOException {
		BufferedWriter writer = null;

		try {
			File logFile = new File("Results");
			logFile.createNewFile();
			System.out.println(logFile.getCanonicalPath());
			writer = new BufferedWriter(new FileWriter(logFile));

			ResponseList<Status> statuses = twitter.getHomeTimeline();
			System.out.println("Showing home timeline.");
			StringBuilder texto = new StringBuilder();


			for(Status status : statuses) {

				if(status.getUser().getName().toLowerCase().contains(data.toLowerCase())) {
					System.out.println(status.getUser().getName());
					texto.append("\n" + status.getUser().getName() + ":" + status.getText() + " \n");
				}

			}

			String second = texto.toString();
			writer.write(second);
			ResultsInterface results = new ResultsInterface(second);
			results.open();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				writer.close();
			} catch (Exception e) {
			}
		}
	}

}
