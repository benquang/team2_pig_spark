inputdata = LOAD 'D:/BIGDATA_ANALIST/pig/SalesTransaction.txt' USING PigStorage(',')
as (id:chararray,date:chararray,productid:chararray,productname:chararray,price:double,quantity:int,customer:chararray,country:chararray);

inputdata = FILTER inputdata BY quantity>0.0;

inputdata = foreach inputdata Generate $0 ..  $7, (price * quantity) AS totalprice;

inputdata_group = GROUP inputdata BY customer;

inputdata_sum = foreach inputdata_group Generate FLATTEN(inputdata.customer), SUM(inputdata.quantity) as totalquantity, SUM(inputdata.totalprice) as totalprice;
inputdata_sum = distinct inputdata_sum;
inputdata_sapxep = ORDER inputdata_sum BY totalprice DESC;
inputdata_gioihan = LIMIT inputdata_sapxep 5;

STORE inputdata_gioihan INTO 'D:/BIGDATA_ANALIST/output/top5customer';
