import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

const Brand = () => {
const [brand,setBrand]=useState();
useEffect(()=>{
    const fetchBrand =async () =>{
        try{
            const response=await axios.get("http://localhost:8080/brands");
            setBrand(response.data);
        }
        catch(error){
            console.error("Lỗi khi gọi API:", error);
        }
    };
    fetchBrand();
    return () => {};
}, []);

  return (
<>
<div className='container'>
<section className="padding-bottom">
  <header className="section-heading heading-line">
    <h4 className="title-section text-uppercase">Chọn thương hiệu</h4>
  </header>
<div className=''>
<ul className="row mt-4 ">
  {brand?.map(brand => (
    <li className="col-md col-6 d-flex justify-content-center align-items-center  "><Link to={`/listpro/${brand.id}`} className="icontext"> <img className="icon-flag-cs" src={`asset/images/icons/${brand.icon}`} /> <span>{brand.brand_name}</span> </Link></li>
  ))}
    </ul>
</div>
</section>

</div>
</>
  )
}

export default Brand