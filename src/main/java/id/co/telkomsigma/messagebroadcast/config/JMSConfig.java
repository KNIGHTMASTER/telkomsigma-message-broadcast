package id.co.telkomsigma.messagebroadcast.config;

import id.co.telkomsigma.messagebroadcast.constant.AppConstant;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@EnableJms
@Configuration
public class JMSConfig {

    @Value("${jmx.listener.autostartup}")
    private String jmxAutoStartup;

    @Value("${spring.activemq.broker-url}")
    String activeMQBrokerUrl;

    @Value("${jms.listener.email.concurrency.size}")
    String emailConcurrencySize;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(activeMQBrokerUrl);
        List<String> trustedPackages = new ArrayList<>();
        trustedPackages.add(AppConstant.Messaging.TrustedPackages.JAVA_LANG);
        trustedPackages.add(AppConstant.Messaging.TrustedPackages.JAVA_MATH);
        trustedPackages.add(AppConstant.Messaging.TrustedPackages.JAVA_UTIL);
        trustedPackages.add(AppConstant.Messaging.TrustedPackages.MAIN_APP);
        factory.setTrustedPackages(trustedPackages);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsBasicEmailListenerContainerFactory() {
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConcurrency(emailConcurrencySize);
        defaultJmsListenerContainerFactory.setAutoStartup(Boolean.parseBoolean(jmxAutoStartup));
        defaultJmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory());
        return defaultJmsListenerContainerFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsAttachedEmailListenerContainerFactory() {
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConcurrency(emailConcurrencySize);
        defaultJmsListenerContainerFactory.setAutoStartup(Boolean.parseBoolean(jmxAutoStartup));
        defaultJmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory());
        return defaultJmsListenerContainerFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsEmailVerificationListenerContainerFactory() {
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConcurrency(emailConcurrencySize);
        defaultJmsListenerContainerFactory.setAutoStartup(Boolean.parseBoolean(jmxAutoStartup));
        defaultJmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory());
        return defaultJmsListenerContainerFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsEmailForgotListenerContainerFactory() {
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConcurrency(emailConcurrencySize);
        defaultJmsListenerContainerFactory.setAutoStartup(Boolean.parseBoolean(jmxAutoStartup));
        defaultJmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory());
        return defaultJmsListenerContainerFactory;
    }

    @Bean(name = AppConstant.Messaging.Queue.EMAIL)
    public Queue queueBasicMail() {
        return new ActiveMQQueue(AppConstant.Messaging.Queue.EMAIL);
    }

    @Bean(name = AppConstant.Messaging.Queue.EMAIL_ATTACHED)
    public Queue queueEmailAttached() {
        return new ActiveMQQueue(AppConstant.Messaging.Queue.EMAIL_ATTACHED);
    }

    @Bean(name = AppConstant.Messaging.Queue.EMAIL_VERIFICATION)
    public Queue queueEmailVerification() {
        return new ActiveMQQueue(AppConstant.Messaging.Queue.EMAIL_VERIFICATION);
    }

    @Bean(name = AppConstant.Messaging.Queue.EMAIL_FORGOT)
    public Queue queueEmailForgot() {
        return new ActiveMQQueue(AppConstant.Messaging.Queue.EMAIL_FORGOT);
    }

}
