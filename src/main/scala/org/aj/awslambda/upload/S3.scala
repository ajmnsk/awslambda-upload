package org.aj.awslambda.upload

import java.io.InputStream

import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.{AmazonS3, AmazonS3Client}

/**
  * Created by ajlnx on 6/23/16.
  */
trait S3 {

  //get s3 client
  val s3Client: AmazonS3 = new AmazonS3Client()

  def upload(bucket: String, key: String, size: Long, content: InputStream): String = {

    //build meta object to describe the data
    val meta: ObjectMetadata = new ObjectMetadata()
    meta.setContentLength(size)

    //store data to s3
    s3Client.putObject(bucket, key, content, meta)

    //get URL for just stored object
    val url = s3Client.getUrl(bucket, key)

    //build return JSON
    val jsonUrl = "{\"url\":\"" + url + "\"}"

    jsonUrl

  }
}
