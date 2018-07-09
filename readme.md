# xke-2018-07-10-serverless-insights-in-60-minutes
A serverless project for the Xebia Knowledge Exchange (XKE) of 2018-07-10, to show how easy it is to create 
serverless insights in 60 minutes.

## resources
The following resources are handy
- [AWS S3](https://docs.aws.amazon.com/AmazonS3/latest/dev/Welcome.html)
- [AWS Glue](https://docs.aws.amazon.com/glue/latest/dg/what-is-glue.html)
- [AWS Kinesis Firehose](https://docs.aws.amazon.com/firehose/latest/dev/what-is-this-service.html)
- [AWS Athena](https://docs.aws.amazon.com/athena/latest/ug/what-is.html)
- [AWS Athena Complex Types and Nested Structures](https://docs.aws.amazon.com/athena/latest/ug/rows-and-structs.html)
- [AWS Athena Querying Arrays](https://docs.aws.amazon.com/athena/latest/ug/querying-arrays.html)
- [AWS Athena Querying JSON](https://docs.aws.amazon.com/athena/latest/ug/querying-JSON.html)
- [AWS Athena Querying Arrays with Complex Types and Nested Structures](https://docs.aws.amazon.com/athena/latest/ug/rows-and-structs.html)
- [AWS Athena Querying Arrays with Maps](https://docs.aws.amazon.com/athena/latest/ug/maps.html) 
- [AWS Lambda](https://docs.aws.amazon.com/lambda/latest/dg/welcome.html)
- [AWS Quicksight](https://docs.aws.amazon.com/quicksight/latest/user/welcome.html)
- [AWS Quicksight Datasource Limits](https://docs.aws.amazon.com/quicksight/latest/user/data-source-limits.html)
- [AWS Quicksight Preparing Data](https://docs.aws.amazon.com/quicksight/latest/user/preparing-data.html)
- [AWS Quicksight Using a SQL Query](https://docs.aws.amazon.com/quicksight/latest/user/adding-a-SQL-query.html)

## Nested JSON in Quicksight
Basically, create a dataset based on a query to a database eg: 

```sql
SELECT CustomerEndpoint.Address as "customerendpoint.address" FROM call_log;
```