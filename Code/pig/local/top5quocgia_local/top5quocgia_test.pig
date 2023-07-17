inputdata = LOAD 'D:/BIGDATA_ANALIST/pig/SalesTransaction.txt' USING PigStorage(',')
as (id:chararray,date:chararray,productid:chararray,productname:chararray,price:double,quantity:int,user:chararray,country:chararray);

inputdata_group = group inputdata by country;
inputdata_count = foreach inputdata_group Generate FLATTEN(inputdata.country) AS country, SUM(inputdata.price) AS total;
inputdata_dist = distinct inputdata_count;

inputdata_sapxep = ORDER inputdata_dist BY total DESC;
inputdata_gioihan = LIMIT inputdata_sapxep 5;

STORE inputdata_gioihan INTO 'D:/BIGDATA_ANALIST/output/out3';





