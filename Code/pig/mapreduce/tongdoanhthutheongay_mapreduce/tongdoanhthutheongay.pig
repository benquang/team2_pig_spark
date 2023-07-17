inputdata = LOAD 'hdfs://localhost:9000/input_data/SalesTransaction.txt' USING PigStorage(',')
as (transactionid:chararray,date:chararray,productid:chararray,productname:chararray,price:double,quantity:int,customer:chararray,country:chararray);

inputdata = FILTER inputdata BY quantity>0.0;

inputdata = foreach inputdata Generate $0 ..  $7, (price * quantity) AS totalprice;

inputdata_group = GROUP inputdata BY date;

inputdata_sum = foreach inputdata_group Generate Flatten(inputdata.date), SUM(inputdata.totalprice)as totalprice;

inputdata_sum = distinct inputdata_sum;

inputdata_sapxep = ORDER inputdata_sum BY totalprice DESC;
inputdata_gioihan = LIMIT inputdata_sapxep 10;

STORE inputdata_gioihan INTO 'hdfs://localhost:9000/pig_output/tongdoanhthutheongay';