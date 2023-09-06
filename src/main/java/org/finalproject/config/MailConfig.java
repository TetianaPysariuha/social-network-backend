package org.finalproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
   private String mailHost;

    @Value("${spring.mail.port}")
   private String mailPort;
    // @Value("${spring.mail.username}")
    @Value("LeraNMU@gmail.com")
   private String mailUserName;
    @Value("sesf uqsn cswa ajyw")
   private String mailPassword;

   @Bean

    public JavaMailSender getJavaMailSender() {

       JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
      javaMailSender.setHost(mailHost);
      javaMailSender.setPort(Integer.parseInt(mailPort));
     javaMailSender.setUsername(mailUserName);
      javaMailSender.setPassword(mailPassword);
       Properties props = javaMailSender.getJavaMailProperties();
       props.put("mail.smtp.starttls.enable","true");
       props.put("mail.smtp.auth","true");
       return javaMailSender ;
   }

}
