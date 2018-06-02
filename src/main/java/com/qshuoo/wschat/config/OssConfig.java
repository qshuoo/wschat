package com.qshuoo.wschat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.comm.Protocol;


/**
 * 阿里云oss
 * @author qshuoo
 *
 */
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssConfig {
	
	private Logger logger = LoggerFactory.getLogger(OssConfig.class);
	
    private String endpoint; // 访问域名
    private String accessKeyId; // 访问密钥id
    private String accessKeySecret; // 访问密钥
	
	@Bean
	public OSS getOssClient() {
		ClientConfiguration configuration = new ClientConfiguration();
		configuration.setProtocol(Protocol.HTTPS);
		OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, configuration);
		logger.info("注入Oss bean 成功");
		return ossClient;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
}
