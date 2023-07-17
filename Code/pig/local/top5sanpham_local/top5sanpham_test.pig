inputdata = LOAD 'D:/BIGDATA_ANALIST/pig/SalesTransaction.txt' USING PigStorage(',')
as (id:chararray,date:chararray,productid:chararray,productname:chararray,price:double,quantity:int,user:chararray,country:chararray);

inputdata_group = group inputdata by productname;
inputdata_count = foreach inputdata_group Generate FLATTEN(inputdata.productname) AS productname, SUM(inputdata.quantity) AS totalquantity, SUM(inputdata.price) AS totalprice;

inputdata_dist = distinct inputdata_count;

inputdata_sapxep = ORDER inputdata_dist BY totalprice DESC;
inputdata_gioihan = LIMIT inputdata_sapxep 10;

STORE inputdata_gioihan INTO 'D:/BIGDATA_ANALIST/output/out4';





