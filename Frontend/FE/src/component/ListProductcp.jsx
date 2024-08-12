import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Pagenavigation from "./Pagenavigation";
const fetchProductsByTagId = async (brandId) => {
  try {
    const productTagsResponse = await axios.get(
      "http://localhost:8080/productBrands"
    );
    const products = productTagsResponse.data
      .filter((item) => item.brand_id == brandId)
      .map((item) => item.productId);
    return products;
  } catch (error) {
    console.error("Lỗi khi gọi API:", error);
    return [];
  }
};

const fetchBrandById = async (brandId) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/brands/${brandId}`
    );

    return response.data; 
  } catch (error) {
    console.error("Lỗi khi lấy thông tin thương hiệu:", error);
    return null;
  }
};
const fetchBrandByProductId = async (productId) => {
  try {
    const productBrandResponse = await axios.get(
      `http://localhost:8080/productBrands`
    );

    const brandIds = productBrandResponse.data
      .filter((item) => item.product_id == productId)
      .map((item) => item.brand_id);

    const brandInfo = await fetchBrandById(brandIds[0]); 
    // console.log('====================================');
    // console.log("brand",brandInfo);
    // console.log('====================================');
    return brandInfo;
  } catch (error) {
    console.error("Lỗi khi lấy thông tin thương hiệu:", error);
    return null;
  }
};




const fetchBrandList = async () => {
  try {
    const response = await axios.get(
      "http://localhost:8080/brands"
    );
    return response.data;
  } catch (error) {
    console.error("Lỗi khi lấy danh sách thương hiệu:", error);
    return [];
  }
};

const ListProduct = () => {
  const [listpro, setListpro] = useState([]);
  const [loading, setLoading] = useState(true);
  const [brandList, setBrandList] = useState([]);
  const { tagId } = useParams();
  const [gallary, setGallary] = useState([]);
  const [productBrand, setProductBrand] = useState([]);
  const [brand, setBrand] = useState([]);
  const [productCount, setProductCount] = useState(0);
  const [selectedBrand, setSelectedBrand] = useState(null);
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const [productbrand,brandReponse,imgReponse] =
          await Promise.all([
            axios.get("http://localhost:8080/productBrands"),
            axios.get("http://localhost:8080/brands"),
            axios.get("http://localhost:8080/galleries")
          ]);
        setBrand(brandReponse.data);
        setProductBrand(productbrand.data);
        setGallary(imgReponse.data);

      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
      }
    };

    fetchProducts();

    return () => { };
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const [brandData, productIds] = await Promise.all([
          fetchBrandList(),
          tagId ? fetchProductsByTagId(tagId) : Promise.resolve([]),
        ]);
    
        const productsResponse = await axios.get(
          "http://localhost:8080/products"
        );
    
        const filteredProducts = productIds.length > 0 ?
          productsResponse.data.filter((item) => productIds.includes(item.product_id)) :
          productsResponse.data;
    
        for (let i = 0; i < filteredProducts.length; i++) {
          const productId = filteredProducts[i].id; 
          const brandInfo = await fetchBrandByProductId(productId); 
          filteredProducts[i].brand = brandInfo ? brandInfo.brand_name : "Unknown"; 
          console.log("brandinfo", brandInfo);
        }
    
        setBrandList(brandData);
        setListpro(filteredProducts);
        setProductCount(filteredProducts.length); 
      } catch (error) {
        console.error("Lỗi khi lấy dữ liệu:", error);
      } finally {
        setLoading(false);
      }
    };
    
    

    fetchData();
  }, [tagId]);
  function formatCurrency(number) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(number);
}
const handleBrandClick = async (brandId) => {
  setLoading(true);
  try {
    const productIds = await fetchProductsByTagId(brandId);
    const productsResponse = await axios.get("http://localhost:8080/products");
    const filteredProducts = productsResponse.data.filter((item) => productIds.includes(item.product_id));

    for (let i = 0; i < filteredProducts.length; i++) {
      const productId = filteredProducts[i].id;
      const brandInfo = await fetchBrandByProductId(productId);
      filteredProducts[i].brand = brandInfo ? brandInfo.brand_name : "Unknown";
    }

    const selectedBrandInfo = await fetchBrandById(brandId);
    setSelectedBrand(selectedBrandInfo);

    setListpro(filteredProducts);
    setProductCount(filteredProducts.length);
  } catch (error) {
    console.error("Lỗi khi lọc sản phẩm theo thương hiệu:", error);
  } finally {
    setLoading(false);
  }
};


  if (loading) {
    return <div>Loading...</div>;
  } else {
  }
  const addToCart = (product) => {
    try {
      let cart = JSON.parse(localStorage.getItem('cart')) || [];
      const existingProductIndex = cart.findIndex(item => item.id === product.id);
  
      if (existingProductIndex !== -1) {
        // Sản phẩm đã tồn tại trong giỏ hàng, tăng số lượng lên 1
        cart[existingProductIndex].quantity++;
      } else {
        // Sản phẩm chưa tồn tại trong giỏ hàng, thêm mới với số lượng là 1
        product.quantity = 1;
        cart.push(product);
      }
  
      // Cập nhật giỏ hàng vào localStorage
      localStorage.setItem('cart', JSON.stringify(cart));
      console.log("Sản phẩm đã được thêm vào giỏ hàng:", product);
      // Cập nhật giao diện người dùng nếu cần
    } catch (error) {
      console.error("Lỗi khi thêm sản phẩm vào giỏ hàng:", error);
    }
  };
  const getProductImages = (productId) => {
    const productImages = gallary.filter((image) => image.productId === productId && image.display_order === 1);
    return productImages.length > 0 ? productImages : []; // Trả về mảng chứa hình ảnh có display_order = 1 hoặc mảng rỗng nếu không có hình ảnh
};


  const getCategoryName = (productId) => {
    // Lấy ra productCategory có productId trùng với productId được truyền vào
    const productCategoryItem = productBrand.find(
      (item) => item.productId === productId
    );
  
    if (productCategoryItem) {
      // Lấy ra categoryName từ category có id tương ứng trong productCategory
      const categoryId = productCategoryItem.brand_id;
  
      const categoryItem = brand.find((item) => item.id === categoryId);
      if (categoryItem) {
        return categoryItem.brand_name;
      }
    }
    return ""; // Trả về rỗng nếu không tìm thấy
  };
  return (
    <>
      <section className="section-content padding-y">
        <div className="container">
          {/* ============================  FILTER TOP  ================================= */}
          <div className="card mb-3">
            <div className="card-body">
             
              <hr />
              <div className="row">
                <div className="col-md-2">Filter by {!selectedBrand ? (""):
                  (selectedBrand.brand_name)}</div> {/* col.// */}
                {/* {selectedBrand && <div>Filtering by: {selectedBrand.brand_name}</div>} */}
                <div className="col-md-10">
                  <ul className="list-inline">
                    
                    <li className="list-inline-item mr-3 dropdown">
                      <a
                        href="#"
                        className="dropdown-toggle"
                        data-toggle="dropdown"
                      >
                        Brand
                      </a>
                      <div className="dropdown-menu">
                        {brandList.map((brand) => (
                          <a key={brand.id}  onClick={() => handleBrandClick(brand.id)} className="dropdown-item">
                            {brand.brand_name}
                          </a>
                        ))}
                      </div>
                    </li>
                    <li className="list-inline-item mr-3">
                      <a href="#">Color</a>
                    </li>
                    <li className="list-inline-item mr-3">
                      <a href="#">Size</a>
                    </li>
                    <li className="list-inline-item mr-3">
                      <div className="form-inline">
                        <label className="mr-2">Price</label>
                        <input
                          className="form-control form-control-sm"
                          placeholder="Min"
                          type="number"
                        />
                        <span className="px-2"> - </span>
                        <input
                          className="form-control form-control-sm"
                          placeholder="Max"
                          type="number"
                        />
                        <button
                          type="submit"
                          className="btn btn-sm btn-light ml-2"
                        >
                          Ok
                        </button>
                      </div>
                    </li>
                   
                  </ul>
                </div>{" "}
                {/* col.// */}
              </div>{" "}
              {/* row.// */}
            </div>{" "}
            {/* card-body .// */}
          </div>{" "}
          {/* card.// */}
          {/* ============================ FILTER TOP END.// ================================= */}
          <header className="mb-3">
            <div className="form-inline">
              <strong className="mr-md-auto">{productCount} Items found </strong>
              <select className="mr-2 form-control">
                <option>Latest items</option>
                <option>Trending</option>
                <option>Most Popular</option>
                <option>Cheapest</option>
              </select>
              <div className="btn-group">
                <a
                  href="page-listing-grid.html"
                  className="btn btn-light active"
                  data-toggle="tooltip"
                  title="List view"
                >
                  <i className="fa fa-bars" />
                </a>
                <a
                  href="page-listing-large.html"
                  className="btn btn-light"
                  data-toggle="tooltip"
                  title="Grid view"
                >
                  <i className="fa fa-th" />
                </a>
              </div>
            </div>
          </header>
          {/* sect-heading */}
          <div className="row">
            {listpro.map((listpro) => (
              <Link to={`/${listpro.product_id}`} className="col-md-3" key={listpro.product_Id}>
                <figure className="card card-product-grid">
                  <div className="img-wrap">
                    <span className="badge badge-danger"> NEW </span>
                    {getProductImages(listpro.product_id).map((image, index) => (
                        <img
                          key={index}
                          src={`${image.image_path}`}
      
                        />
                      ))}
                  </div>{" "}
                  {/* img-wrap.// */}
                  <figcaption className="info-wrap">
                    <a href="#" className="title mb-2">
                      {listpro.product_name}
                    </a>
                    <p className="brand-name">Thương hiệu:                     {getCategoryName(listpro.product_id)}
</p> {/* Hiển thị tên thương hiệu */}
                    <div className="price-wrap">
                      <span className="price">  {formatCurrency(listpro.regular_price)}</span>
                    </div>{" "}
                    <hr />
                    <Link to={`/cart`} onClick={() => addToCart(listpro)} className="btn btn-primary">
                      Add To Cart
                    </Link>
                  </figcaption>
                </figure>
              </Link>
            ))}
          </div>{" "}
          {/* row end.// */}
    <Pagenavigation/>
          <div className="box text-center">
            <p>Did you find what you were looking for？</p>
            <a href className="btn btn-light">
              Yes
            </a>
            <a href className="btn btn-light">
              No
            </a>
          </div>
        </div>{" "}
        {/* container .//  */}
      </section>
    </>
  );
};

export default ListProduct;
