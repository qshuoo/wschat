package com.qshuoo.wschat.service.impl;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.ObjectMetadata;
import com.qshuoo.wschat.pojo.OssHelper;
import com.qshuoo.wschat.service.OssService;

/**
 * 阿里云oss
 * @author qshuoo
 *
 */
@Service
public class OssServiceImpl implements OssService{
	
	@Value("${aliyun.oss.prefixUrl}")
	private String prefix;

	@Value("${aliyun.oss.suffixUrl}")
	private String suffix;
	
	@Value("${aliyun.oss.bucketName}")
	private String bucketName;
	
	@Autowired
	private OSS ossClient;
	
	@Override
	public String upload(OssHelper helper) {
		ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentDisposition(helper.getFileName());
        metadata.setContentType(helper.getContentType());
        metadata.setContentEncoding("utf-8");
        String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(helper.getBytes()));
        metadata.setContentMD5(md5);

        ossClient.putObject(bucketName, helper.getFileName(),
                new ByteArrayInputStream(helper.getBytes()), metadata);

		return "https://" + prefix + "/" + helper.getFileName() + suffix;
	}

}
