import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

export default function Home() {
  /*const [users, setUsers] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    const result = await axios.get("http://localhost:8080/users");
    setUsers(result.data);
  };

  const deleteUser = async (id) => {
    await axios.delete(`http://localhost:8080/user/${id}`);
    loadUsers();
  };*/

  const [quocgias,setQuocgias] = useState([]);
  const [sanphams,setSamphams] = useState([]);
  const [giaodichs,setGiaodichs] = useState([]);
  const [tongdoanhthus,setTongdoanhthus] = useState([]);
  const [customers,setCustomers] = useState([]);


  useEffect(()=>{
    loadQuocgias();
  },[]);

  const loadQuocgias=async()=>{
    const result=await axios.get("http://localhost:8080/data_quocgia");
    setQuocgias(result.data);

    const result1=await axios.get("http://localhost:8080/data_sanpham");
    setSamphams(result1.data);
    
    const result2=await axios.get("http://localhost:8080/data_transaction");
    setGiaodichs(result2.data);

    const result3=await axios.get("http://localhost:8080/data_tongdoanhthu");
    setTongdoanhthus(result3.data);

    const result4=await axios.get("http://localhost:8080/data_customer");
    setCustomers(result4.data);
};


  return (
    <div>

      
      <nav class="navbar navbar-expand-lg bg-body-tertiary" style={{'background-color': '#e3f2fd'}}>
     <div class="container-fluid">
    <a class="navbar-brand" href="http://localhost:3000/" style={{'margin-left':'115px'}}>Nhóm 02 - Statistic</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item" style={{'margin-left': '60px','line-height':'40px'}}>
          <a class="nav-link active" aria-current="page" href="http://localhost:3000/">Apache Pig</a>
        </li>
        <li class="nav-item" style={{'margin-left': '50px','line-height':'40px'}}>
          <a class="nav-link active" aria-current="page" href="/spark">Spark - MLLib - KMeans</a>
        </li>

        <li class="nav-item" style={{'margin-left':'260px'}}>
          <a class="nav-link active" aria-current="page"  style={{'font-size':'15px','text-align':'left','line-height':'20px'}}>
            Nguyễn Trí Dũng - 20133029<br></br>Nguyễn Khoa Quang Thắng - 20133090</a>
        </li>
      </ul>
      
       </div>
       </div>
    </nav>

      <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr style={{'border-bottom-width': '1px'}}>
              <th scope="col" style={{'font-size': '25px','border-bottom-width': '0px','width':'30%'}}>01.</th>
              <th scope="col" style={{'color':'#bb2d3b','font-size': '25px','float':'left','border-bottom-width': '0px'}}>Top 5 Quốc Gia Tiêu Thụ Nhiều Nhất</th>


            </tr>
            <tr>
              <th scope="col">Country</th>
              <th scope="col">TotalPrice</th>
            </tr>
          </thead>
          <tbody>

              {
                quocgias.map((quocgia,index) => (
                  <tr>
                    <td>{quocgia.country}</td>
                    <td>{quocgia.totalprice}</td>
                  </tr>
                ))
              }


          </tbody>
        </table>
      </div>


      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr style={{'border-bottom-width': '1px'}}>
              <th scope="col" style={{'font-size': '25px','border-bottom-width': '0px','width':'30%'}}>02.</th>
              <th scope="col" style={{'color':'#0a426e','font-size': '25px','float':'left','border-bottom-width': '0px'}}>Top 5 Sản Phẩm Được Mua Nhiều Nhất</th>
        

            </tr>
            <tr>
              <th scope="col">ProductName</th>
              <th scope="col">TotalQuantity</th>
              <th scope="col">TotalPrice</th>
            </tr>
          </thead>
          <tbody>

              {
                sanphams.map((sanpham,index) => (
                  <tr>
                    <td>{sanpham.productname}</td>
                    <td>{sanpham.quantity}</td>
                    <td>{sanpham.totalprice}</td>
                  </tr>
                ))
              }


          </tbody>
        </table>
      </div>

      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr style={{'border-bottom-width': '1px'}}>
              <th scope="col" style={{'font-size': '25px','border-bottom-width': '0px','width':'30%'}}>03.</th>
              <th scope="col" style={{'color':'#bb2d3b','font-size': '25px','float':'left','border-bottom-width': '0px'}}>Top 5 Giao Dịch Nhiều Nhất</th>
        

            </tr>
            <tr>
              <th scope="col">Transaction</th>
              <th scope="col">TotalQuantity</th>
              <th scope="col">TotalPrice</th>
            </tr>
          </thead>
          <tbody>

              {
                giaodichs.map((giaodich,index) => (
                  <tr>
                    <td>{giaodich.id}</td>
                    <td>{giaodich.quantity}</td>
                    <td>{giaodich.totalprice}</td>
                  </tr>
                ))
              }


          </tbody>
        </table>
      </div>

      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr style={{'border-bottom-width': '1px'}}>
              <th scope="col" style={{'font-size': '25px','border-bottom-width': '0px','width':'30%'}}>04.</th>
              <th scope="col" style={{'color':'#0a426e','font-size': '25px','float':'left','border-bottom-width': '0px'}}>Tổng Doanh Thu 10 Ngày Cao Nhất</th>
        

            </tr>
            <tr>
              <th scope="col">Date</th>
              <th scope="col">TotalPrice</th>
            </tr>
          </thead>
          <tbody>

              {
                tongdoanhthus.map((tongdoanhthu,index) => (
                  <tr>
                    <td>{tongdoanhthu.date}</td>
                    <td>{tongdoanhthu.totalprice}</td>
                  </tr>
                ))
              }


          </tbody>
        </table>
      </div>

      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr style={{'border-bottom-width': '1px'}}>
              <th scope="col" style={{'font-size': '25px','border-bottom-width': '0px','width':'30%'}}>05.</th>
              <th scope="col" style={{'color':'#bb2d3b','font-size': '25px','float':'left','border-bottom-width': '0px'}}>Top 5 Khách Hàng Mua Nhiều Nhất</th>
        

            </tr>
            <tr>
              <th scope="col">CustomerId</th>
              <th scope="col">TotalQuantity</th>
              <th scope="col">TotalPrice</th>
            </tr>
          </thead>
          <tbody>

              {
                customers.map((customer,index) => (
                  <tr>
                    <td>{customer.user}</td>
                    <td>{customer.quantity}</td>
                    <td>{customer.totalprice}</td>
                  </tr>
                ))
              }


          </tbody>
        </table>
      </div>

      </div>


    </div>
  );
}
