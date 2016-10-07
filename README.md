AWS Lambda to upload file to s3 using scala
=========================================================

Function is implemented to be used with AWS API Gateway.

## Lambda Implementation

[AWS documentation](http://docs.aws.amazon.com/lambda/latest/dg/with-s3-example-deployment-pkg.html) was used as a reference to create, and setup the function.


## API Gateway setup

Following are references on how to create and configure API Gateway:

* [getting started](http://docs.aws.amazon.com/apigateway/latest/developerguide/getting-started.html)
* [api gateway create](http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-create-api-step-by-step.html)


## Usage

Lambda is expecting JSON object as an input parameter:

```
{
 "fileName" : "dancing.gif",
 "bucket" : "com.mycompany",
 "key" : "20161007/party/boss",
 "content" : [71,73,70,56,55,97,100,0,75,0,-16,0, ... ]
}
```

```content``` field has file data converted into an array of bytes.

Here is an upload call example using API Gateway:

```
curl -X POST https://apiidxxx.execute-api.us-west-2.amazonaws.com/upload/data \
-H 'Content-Type: application/json' \
-d '{"fileName" : "dancing.gif", "bucket" : "com.mycompany", "key" : "20161007/party/boss", "content" : [71,73,70,56,55,97,100,0,75,0,-16,0, ... ]}'
```

## Author & license

If you have any questions regarding this project contact:
Andrei <ajmnsk@gmail.com>
For licensing info see LICENSE file in project's root directory.
