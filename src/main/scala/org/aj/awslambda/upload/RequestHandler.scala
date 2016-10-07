package org.aj.awslambda.upload

import java.io.{ByteArrayInputStream, InputStream, OutputStream}
//import java.time.Clock

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
  * Lambda to upload data to s3
  *
  */
class RequestHandler extends S3 {

  /**
    * Object to handle JSON <> Scala Object conversion
    */
  private val scalaMapper = { new ObjectMapper().registerModule(new DefaultScalaModule) }

  /**
    * Synchronized Request Response call.
    *
    * @param input The JSON Object representing data to be uploaded to s3 storage
    * @param output The JSON Object containing URL to stored object
    */
  def s3Upload(input: InputStream, output: OutputStream): Unit = {

    //deserialize json into object
    val uploadEvent = scalaMapper.readValue(input, classOf[UploadEvent])

    /**
      * Build key
      *
      * 1. key
      * 2. data file name
      *
      * Clock.systemUTC().instant().toString.take(10).replace("-", "/"),
      *
      */
    val key = "%s%s".format(
      (if (uploadEvent.key.size > 0) uploadEvent.key + "/" else ""),
      uploadEvent.fileName
    )

    //build stream using binary content passed
    val is: InputStream = new ByteArrayInputStream(uploadEvent.content)

    val url = upload(uploadEvent.bucket, key, uploadEvent.content.size, is)

    //return the result
    output.write(url.getBytes("UTF-8"))

  }

}
