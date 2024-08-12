import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
const Content = () => {
  const [products, setProducts] = useState([]);
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/products");
        console.log("====================================");
        console.log(response.data);
        console.log("====================================");
        setProducts(response.data);
      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
      }
    };

    fetchProducts();

    return () => {};
  }, []);
  function formatCurrency(number) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(number);
}
const addToCart = (product) => {
  try {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    const existingProductIndex = cart.findIndex(item => item.product_id === product.product_id);

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


  return (
    <div>
      {/* ========================= SECTION MAIN  ========================= */}
      <section className="section-intro padding-y">
        <div className="container">
          {/* ==============  COMPONENT SLIDER  BOOTSTRAP ============  */}
          <div
            id="carousel1_indicator"
            className="slider-home-banner carousel slide"
            data-ride="carousel"
          >
            <ol className="carousel-indicators">
              <li
                data-target="#carousel1_indicator"
                data-slide-to={0}
                className="active"
              />
              <li data-target="#carousel1_indicator" data-slide-to={1} />
            </ol>
            <div className="carousel-inner">
              <div className="carousel-item active">
                <img
                  src="asset/images/banners/slide-lg-3.jpg"
                  alt="First slide"
                />
              </div>
              <div className="carousel-item">
                <img
                  src="asset/images/banners/slide-lg-2.jpg"
                  alt="Second slide"
                />
              </div>
            </div>
            <a
              className="carousel-control-prev"
              href="#carousel1_indicator"
              role="button"
              data-slide="prev"
            >
              <span className="carousel-control-prev-icon" aria-hidden="true" />
              <span className="sr-only">Previous</span>
            </a>
            <a
              className="carousel-control-next"
              href="#carousel1_indicator"
              role="button"
              data-slide="next"
            >
              <span className="carousel-control-next-icon" aria-hidden="true" />
              <span className="sr-only">Next</span>
            </a>
          </div>
          {/* ============ COMPONENT SLIDER BOOTSTRAP end.// ===========  .// */}
        </div>{" "}
        {/* container end.// */}
      </section>
      {/* ========================= SECTION MAIN END// ========================= */}
      <div className="container">
        {/* =============== SECTION 1 =============== */}
        <section className="padding-bottom">
          <header className="section-heading mb-4">
            <h3 className="title-section">Recommended items</h3>
          </header>
          <div className="row">
          {products.map(product => (
            <div className="col-xl-3 col-lg-3 col-md-4 col-6" key={product.product_id}>
              <div className="card card-product-grid">
                <a href="#" className="img-wrap position-relative">
                  <img  src={`asset/images/items/${product.sku}`} />
                </a>
                <div className="overlay position-absolute top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center">
                  <a href="#" className="img-wrap position-relative"></a>
                  <div className="overlay-content text-center">
                    <a href="#" className="img-wrap position-relative"></a>
                    <Link to={`/cart`} onClick={() => addToCart(product)} className="btn btn-primary">
                      Add To Cart
                    </Link>
                    <Link to={`/${product.product_id}`} className="btn btn-secondary">
                      Quick View
                    </Link>
                  </div>
                </div>
                <figcaption className="info-wrap">
                  <ul className="rating-stars mb-1">
                    <li style={{ width: "80%" }} className="stars-active">
                      <img src="asset/images/icons/stars-active.svg" alt />
                    </li>
                    <li>
                      <img src="asset/images/icons/starts-disable.svg" alt />
                    </li>
                  </ul>
                  <div>
                    <a href="#" className="text-muted">
                      Clothes
                    </a>
                    <a href="#" className="title title_name">
                    {product.product_name}
                    </a>
                  </div>
                  <div className="price h5 mt-2">{formatCurrency(product.regular_price)}</div> {/* price.// */}
                </figcaption>
              </div>
            </div>
          ))}
          </div>
          {/* row.// */}
        </section>
        {/* =============== SECTION 1 END =============== */}
        {/* =============== SECTION BANNER =============== */}

        {/* =============== SECTION BANNER .//END =============== */}
        {/* =============== SECTION 2 =============== */}
        <section className="padding-bottom">
          <header className="section-heading mb-4">
            <h3 className="title-section">Daily deals</h3>
          </header>
          <div className="row row-sm">
            <div className="col-xl-2 col-lg-3 col-md-4 col-6">
              <div href="#" className="card card-sm card-product-grid">
                <a href="#" className="img-wrap">
                  <b className="badge badge-danger mr-1">10% OFF</b>
                  <img src="asset/images/items/9.jpg" />
                </a>
                <figcaption className="info-wrap">
                  <a href="#" className="title">
                    Just another product name
                  </a>
                  <div className="price-wrap">
                    <span className="price">$45</span>
                    <del className="price-old">$90</del>
                  </div>{" "}
                  {/* price-wrap.// */}
                </figcaption>
              </div>
            </div>{" "}
            {/* col.// */}
            <div className="col-xl-2 col-lg-3 col-md-4 col-6">
              <div href="#" className="card card-sm card-product-grid">
                <a href="#" className="img-wrap">
                  <b className="badge badge-danger mr-1">85% OFF</b>
                  <img src="asset/images/items/10.jpg" />
                </a>
                <figcaption className="info-wrap">
                  <a href="#" className="title">
                    Some item name here
                  </a>
                  <div className="price-wrap">
                    <span className="price">$45</span>
                    <del className="price-old">$90</del>
                  </div>{" "}
                  {/* price-wrap.// */}
                </figcaption>
              </div>
            </div>{" "}
            {/* col.// */}
            <div className="col-xl-2 col-lg-3 col-md-4 col-6">
              <div href="#" className="card card-sm card-product-grid">
                <a href="#" className="img-wrap">
                  <b className="badge badge-danger mr-1">10% OFF</b>
                  <img src="asset/images/items/11.jpg" />
                </a>
                <figcaption className="info-wrap">
                  <a href="#" className="title">
                    Great product name here
                  </a>
                  <div className="price-wrap">
                    <span className="price">$45</span>
                    <del className="price-old">$90</del>
                  </div>{" "}
                  {/* price-wrap.// */}
                </figcaption>
              </div>
            </div>{" "}
            {/* col.// */}
            <div className="col-xl-2 col-lg-3 col-md-4 col-6">
              <div href="#" className="card card-sm card-product-grid">
                <a href="#" className="img-wrap">
                  <b className="badge badge-danger mr-1">90% OFF</b>
                  <img src="asset/images/items/12.jpg" />
                </a>
                <figcaption className="info-wrap">
                  <a href="#" className="title">
                    Just another product name
                  </a>
                  <div className="price-wrap">
                    <span className="price">$45</span>
                    <del className="price-old">$90</del>
                  </div>{" "}
                  {/* price-wrap.// */}
                </figcaption>
              </div>
            </div>{" "}
            {/* col.// */}
            <div className="col-xl-2 col-lg-3 col-md-4 col-6">
              <div href="#" className="card card-sm card-product-grid">
                <a href="#" className="img-wrap">
                  <b className="badge badge-danger mr-1">20% OFF</b>
                  <img src="asset/images/items/5.jpg" />
                </a>
                <figcaption className="info-wrap">
                  <a href="#" className="title">
                    Just another product name
                  </a>
                  <div className="price-wrap">
                    <span className="price">$45</span>
                    <del className="price-old">$90</del>
                  </div>{" "}
                  {/* price-wrap.// */}
                </figcaption>
              </div>
            </div>{" "}
            {/* col.// */}
            <div className="col-xl-2 col-lg-3 col-md-4 col-6">
              <div href="#" className="card card-sm card-product-grid">
                <a href="#" className="img-wrap">
                  <b className="badge badge-danger mr-1">20% OFF</b>
                  <img src="asset/images/items/6.jpg" />
                </a>
                <figcaption className="info-wrap">
                  <a href="#" className="title">
                    Some item name here
                  </a>
                  <div className="price-wrap">
                    <span className="price">$45</span>
                    <del className="price-old">$90</del>
                  </div>{" "}
                  {/* price-wrap.// */}
                </figcaption>
              </div>
            </div>{" "}
            {/* col.// */}
          </div>{" "}
          {/* row.// */}
        </section>
        {/* =============== SECTION 2 END =============== */}
        {/* =============== SECTION BANNER =============== */}
        <section className="padding-bottom">
          <article className="box d-flex flex-wrap align-items-center p-5 bg-secondary">
            <div className="text-white mr-auto">
              <h3>Looking for fashion? </h3>
              <p> Popular items, discounts and free shipping </p>
            </div>
            <div className="mt-3 mt-md-0">
              <a href className="btn btn-outline-light">
                Learn more
              </a>
            </div>
          </article>
        </section>
        {/* =============== SECTION BANNER .//END =============== */}
      </div>
    </div>
  );
};

export default Content;
