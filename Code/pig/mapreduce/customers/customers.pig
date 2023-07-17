inputdata = LOAD 'hdfs://localhost:9000/input_data/SalesTransaction.txt' USING PigStorage(',')
as (id:chararray,date:chararray,productid:chararray,productname:chararray,price:double,quantity:int,user:chararray,country:chararray);

inputdata = FILTER inputdata BY quantity>0.0;

inputdata = foreach inputdata Generate $0 ..  $7, (price * quantity) AS totalprice;

inputdata_group = GROUP inputdata BY user;

inputdata_sum = foreach inputdata_group Generate FLATTEN(inputdata.user), SUM(inputdata.quantity),SUM(inputdata.totalprice) as totalprice;
inputdata_sum = distinct inputdata_sum;

STORE inputdata_sum INTO 'hdfs://localhost:9000/pig_output/customers';
