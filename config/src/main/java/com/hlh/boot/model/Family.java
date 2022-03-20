package com.hlh.boot.model;

import com.hlh.boot.util.MixPropertySourceFactory;
import lombok.Data;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author hlh
 */
@Data
@Component
@Validated
@ConfigurationProperties(prefix = "family")
@PropertySource(value = {"classpath:family.yml"},factory = MixPropertySourceFactory.class)
public class Family {
    @Length(min = 5,max = 20, message = "家庭名称长度必须在5-20之间")
    private String familyName;

    private Father father;

    private Mother mother;

    private Child child;
}
