import React from "react";
import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <div >

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
    </div>
  );
}
