import os
import sys
import pyspark
from pyspark.sql import SparkSession
from pyspark.sql.functions import col
from pyspark.sql.types import IntegerType,StringType,DoubleType
from pyspark.ml.evaluation import ClusteringEvaluator
from subprocess import PIPE, Popen
os.environ['PYSPARK_PYTHON'] = sys.executable
os.environ['PYSPARK_DRIVER_PYTHON'] = sys.executable
spark = SparkSession.builder.getOrCreate()


from pyspark.ml.clustering import KMeans
from pyspark.ml.evaluation import ClusteringEvaluator
from pyspark.ml.feature import VectorAssembler,StandardScaler
# Loads data.
dataset = spark.read.csv("hdfs://localhost:9000/pig_output/customers/part-r-00000", sep='\t' ,lineSep= '\n',header=False)
dataset = dataset.withColumnRenamed('_c0','CustomerNo')
dataset = dataset.withColumnRenamed('_c1','TotalQuantity')
dataset = dataset.withColumnRenamed('_c2','TotalMoney')
dataset = dataset.dropna()

dataset = dataset.withColumn("CustomerNo", dataset["CustomerNo"].cast(IntegerType()))
dataset = dataset.withColumn("CustomerNo", dataset["CustomerNo"].cast(StringType()))
dataset = dataset.withColumn("TotalQuantity", dataset["TotalQuantity"].cast(IntegerType()))
dataset = dataset.withColumn("TotalMoney", dataset["TotalMoney"].cast(DoubleType()))

feat_cols = ['TotalQuantity','TotalMoney']
feat_vector = VectorAssembler(inputCols=feat_cols, outputCol='features')
data = feat_vector.transform(dataset).select('CustomerNo','features')
final_data = feat_vector.transform(dataset)

scaler = StandardScaler(inputCol='features',outputCol='zfeatures',withStd=True,withMean=False)
scalerModel = scaler.fit(final_data)
cluster_inputdata = scalerModel.transform(final_data)

kmeans4 = KMeans(featuresCol='zfeatures',k=4)
kmeans5 = KMeans(featuresCol='zfeatures',k=5)

model_k4 = kmeans4.fit(cluster_inputdata)
model_k5 = kmeans5.fit(cluster_inputdata)
#---------k = 4--------------------
cluster_output = model_k4.transform(cluster_inputdata).select('CustomerNo','TotalQuantity','TotalMoney','prediction')
rows = cluster_output.collect()
df_output = spark.createDataFrame(rows)
pandasDF = df_output.toPandas()
pandasDF.to_csv('D:/BIGDATA_ANALIST/spark/Kmean4.csv',index = False)

#---------k = 5--------------------
cluster_output = model_k5.transform(cluster_inputdata).select('CustomerNo','TotalQuantity','TotalMoney','prediction')
rows = cluster_output.collect()
df_output = spark.createDataFrame(rows)
pandasDF = df_output.toPandas()
pandasDF.to_csv('D:/BIGDATA_ANALIST/spark/Kmean5.csv',index = False)

#---------------Put hdfs--------------
filePath = r'D:/BIGDATA_ANALIST/spark/Kmean4.csv'
hdfsPath = r'/spark_output'
put = Popen(["hdfs", "dfs", "-put", filePath, hdfsPath], stdin=PIPE, bufsize=-1,shell=True)
put.communicate()

filePath = r'D:/BIGDATA_ANALIST/spark/Kmean5.csv'
put = Popen(["hdfs", "dfs", "-put", filePath, hdfsPath], stdin=PIPE, bufsize=-1,shell=True)
put.communicate()

