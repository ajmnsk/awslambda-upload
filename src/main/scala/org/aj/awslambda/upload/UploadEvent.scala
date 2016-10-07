package org.aj.awslambda.upload

/**
  * @param fileName The name of the data file uploaded
  * @param bucket The s3 bucket to upload to
  * @param key The key to use within the bucket
  * @param content The data content
  */
case class UploadEvent(fileName: String, bucket: String, key: String, content: Array[Byte])
