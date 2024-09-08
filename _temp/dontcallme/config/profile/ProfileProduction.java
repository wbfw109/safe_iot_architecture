package com.practice.dontcallme.config.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile(value="prod")
@PropertySource({"classpath:application-prod.properties"})
public class ProfileProduction {
    // Skipped Configurations
}
