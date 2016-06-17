package at.ac.tuwien.service;

import at.ac.tuwien.client.NotificationClient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterServiceImpl implements TwitterService {
    private Twitter twitter;
    @Value("${consumer.key}")
    private String consumerKey;
    @Value("${consumer.secret}")
    private String consumerSecret;
    @Autowired
    private ThresholdService thresholdService;

    private OAuth2Token getToken() {
        OAuth2Token token = null;
        ConfigurationBuilder cb;

        cb = new ConfigurationBuilder();
        cb.setApplicationOnlyAuthEnabled(true);
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);

        try
        {
            token = new TwitterFactory(cb.build())
                    .getInstance().getOAuth2Token();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Could not get OAUTH Token", e);
        }

        return token;
    }

    @PostConstruct
    private void init() {
        OAuth2Token token = this.getToken();

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setApplicationOnlyAuthEnabled(true);
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuth2TokenType(token.getTokenType());
        cb.setOAuth2AccessToken(token.getAccessToken());

        twitter = new TwitterFactory(cb.build()).getInstance();
    }

    @Override
    public String get(String request) {
        thresholdService.performRequest();

        List<String> tweets = new ArrayList<String>();
        Query query = new Query(request);
        query.setCount(1);
        //query.setLang("en");
        QueryResult result;
        try {
            result = twitter.search(query);
            for (Status status : result.getTweets()) {
                //System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
                tweets.add(status.getText());
            }
        } catch (TwitterException e) {
            throw new RuntimeException(e);
        }

        return new Gson().toJson(tweets);
    }
}
