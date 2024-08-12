import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

const DetailPro = () => {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [gallary, setGallary] = useState([]);
  const [productbrand, setProductBrand] = useState([]);
  const [idproductbrand, setIdproductbrand] = useState([]);
  const [mainImage, setMainImage] = useState(null);
  const [brandProducts, setBrandProducts] = useState([]);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const [
          productbrand,
          brandReponse,
          productReponse,
          galleryReponse,
          productTagReponse,
        ] = await Promise.all([
          axios.get("http://localhost:8080/productBrands"),
          axios.get("http://localhost:8080/brands"),
          axios.get(`http://localhost:8080/products/${id}`),
          axios.get("http://localhost:8080/galleries"),
          axios.get("http://localhost:8080/productTags"),
        ]);
        setProduct(productReponse.data);
        setGallary(galleryReponse.data);
        setProductBrand(productbrand.data);
      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
      }
    };

    fetchProduct();
  }, [id]);

  useEffect(() => {
    if (product && gallary.length > 0) {
      const initialImage = gallary.find(
        (image) => image.productId === product.product_id && image.display_order === 1
      );
      
      if (initialImage) {
        setMainImage(initialImage.image_path);
      }
    }
  }, [product, gallary]);
  useEffect(() => {
    if (Array.isArray(productbrand) && productbrand.length > 0) {
      const product = productbrand.find(p => p.productId === id);

      if (product) {
        const idbrand = product.brand_id;
        const brandproducts = productbrand.filter(p => p.brand_id === idbrand);
        
        if (Array.isArray(brandproducts) && brandproducts.length > 0) {
          const productIds = brandproducts.map(p => p.productId);
          setIdproductbrand(productIds)
        } else {
          console.log("No products found for the given brand");
        }
      } else {
        console.log("Product not found");
      }
    }
  }, [id, productbrand]);

  const getProductImages = (productId) => {
    return gallary.filter((image) => image.productId === productId);
  };
  const getProductImage = (productId) => {
    return gallary.filter((image) => image.productId === productId&& image.display_order === 1);
  };
  useEffect(() => {
    const fetchBrandProducts = async () => {
      if (idproductbrand.length > 0) {
        try {
          const productDetails = await Promise.all(
            idproductbrand.map(productId => axios.get(`http://localhost:8080/products/${productId}`))
          );
          setBrandProducts(productDetails.map(response => response.data));
        } catch (error) {
          console.error("Lỗi khi gọi API sản phẩm:", error);
        }
      }
    };

    fetchBrandProducts();
  }, [idproductbrand]);
  const handleThumbnailClick = (imagePath) => {
    setMainImage(imagePath);
  };

  if (!product) {
    return <div>Loading...</div>;
  }
  function formatCurrency(number) {
    return new Intl.NumberFormat("vi-VN", {
      style: "currency",
      currency: "VND",
    }).format(number);
  }
  const addToCart = (product) => {
    try {
      let cart = JSON.parse(localStorage.getItem("cart")) || [];
      const existingProductIndex = cart.findIndex(
        (item) => item.id === product.id
      );

      if (existingProductIndex !== -1) {
        // Sản phẩm đã tồn tại trong giỏ hàng, tăng số lượng lên 1
        cart[existingProductIndex].quantity++;
      } else {
        // Sản phẩm chưa tồn tại trong giỏ hàng, thêm mới với số lượng là 1
        product.quantity = 1;
        cart.push(product);
      }

      // Cập nhật giỏ hàng vào localStorage
      localStorage.setItem("cart", JSON.stringify(cart));
      console.log("Sản phẩm đã được thêm vào giỏ hàng:", product);
      // Cập nhật giao diện người dùng nếu cần
    } catch (error) {
      console.error("Lỗi khi thêm sản phẩm vào giỏ hàng:", error);
    }
  };

  return (
    <div>
      <>
        <section className="py-3 bg-light">
          <div className="container">
            <ol className="breadcrumb">
              <li className="breadcrumb-item">
                <a href="#">Home</a>
              </li>
              <li className="breadcrumb-item">
                <a href="#">Category name</a>
              </li>
              <li className="breadcrumb-item">
                <a href="#">Sub category</a>
              </li>
              <li className="breadcrumb-item active" aria-current="page">
                Items
              </li>
            </ol>
          </div>
        </section>
        {/* ========================= SECTION CONTENT ========================= */}
        <section className="section-content bg-white padding-y">
          <div className="container">
            {/* ============================ ITEM DETAIL ======================== */}
            <div className="row">
              <aside className="col-md-6">
                <div className="card">
                  <article className="gallery-wrap">
                    <div className="img-big-wrap">
                      <div className="single-item">
                        {" "}
                        <a href="#">
                {mainImage && <img src={mainImage} alt="Main Product" />}
              </a>
                      </div>
                    </div>{" "}
                    {/* slider-product.// */}
                 <div className="thumbs-wrap">
            {getProductImages(product.product_id).map((image, index) => (
              <a
                href="#"
                key={index}
                className="item-thumb"
                onClick={(e) => {
                  e.preventDefault();
                  handleThumbnailClick(image.image_path);
                }}
              >
                <img
                  src={image.image_path}
                  alt={`Product Image ${index + 1}`}
                />
              </a>
            ))}
          </div>
                    {/* slider-nav.// */}
                  </article>{" "}
                  {/* gallery-wrap .end// */}
                </div>{" "}
                {/* card.// */}
              </aside>
              <main className="col-md-6">
                <article className="product-info-aside">
                  <h2 className="title mt-3">{product.product_name}</h2>
                  <div className="rating-wrap my-3">
                    <ul className="rating-stars">
                      <li style={{ width: "80%" }} className="stars-active">
                        <i className="fa fa-star" />{" "}
                        <i className="fa fa-star" />
                        <i className="fa fa-star" />{" "}
                        <i className="fa fa-star" />
                        <i className="fa fa-star" />
                      </li>
                      <li>
                        <i className="fa fa-star" />{" "}
                        <i className="fa fa-star" />
                        <i className="fa fa-star" />{" "}
                        <i className="fa fa-star" />
                        <i className="fa fa-star" />
                      </li>
                    </ul>
                    <small className="label-rating text-muted">
                      132 reviews
                    </small>
                    <small className="label-rating text-success">
                      {" "}
                      <i className="fa fa-clipboard-check" /> 154 orders{" "}
                    </small>
                  </div>{" "}
                  {/* rating-wrap.// */}
                  <div className="mb-3">
                    <var className="price h4">
                      {formatCurrency(product.regular_price)}
                    </var>
                  </div>{" "}
                  {/* price-detail-wrap .// */}
                  <p>{product.product_description}</p>
                  <dl className="row">
                    <dt className="col-sm-3">Manufacturer</dt>
                    <dd className="col-sm-9">
                      <a href="#">Great textile Ltd.</a>
                    </dd>
                    <dt className="col-sm-3">Article number</dt>
                    <dd className="col-sm-9">596 065</dd>
                    <dt className="col-sm-3">Guarantee</dt>
                    <dd className="col-sm-9">2 year</dd>
                    <dt className="col-sm-3">Delivery time</dt>
                    <dd className="col-sm-9">3-4 days</dd>
                    <dt className="col-sm-3">Availabilty</dt>
                    <dd className="col-sm-9">in Stock</dd>
                  </dl>
                  <div className="form-row  mt-4">
                    <div className="form-group col-md flex-grow-0">
                      <div className="input-group mb-3 input-spinner">
                        <div className="input-group-prepend">
                          <button
                            className="btn btn-light"
                            type="button"
                            id="button-plus"
                          >
                            {" "}
                            +{" "}
                          </button>
                        </div>
                        <input
                          type="text"
                          className="form-control"
                          defaultValue={1}
                        />
                        <div className="input-group-append">
                          <button
                            className="btn btn-light"
                            type="button"
                            id="button-minus"
                          >
                            {" "}
                            −{" "}
                          </button>
                        </div>
                      </div>
                    </div>{" "}
                    {/* col.// */}
                    <div className="form-group col-md">
                      <Link
                        to={`/cart`}
                        onClick={() => addToCart(product)}
                        className="btn btn-primary"
                      >
                        Add To Cart
                      </Link>
                    </div>{" "}
                    {/* col.// */}
                  </div>{" "}
                  {/* row.// */}
                </article>{" "}
                {/* product-info-aside .// */}
              </main>{" "}
              {/* col.// */}
            </div>{" "}
            {/* row.// */}
            {/* ================ ITEM DETAIL END .// ================= */}
          </div>{" "}
          {/* container .//  */}
        </section>
        {/* ========================= SECTION CONTENT END// ========================= */}
        {/* ========================= SECTION  ========================= */}
        <section className="section-name padding-y bg">
          <div className="container">
            <div className="row">
              <div className="col-md-8">
                <h5 className="title-description">Description</h5>
                <p>
                  Lava stone grill, suitable for natural gas, with cast-iron
                  cooking grid, piezo ignition, stainless steel burners, water
                  tank, and thermocouple. Thermostatic adjustable per zone.
                  Comes complete with lava rocks. Adjustable legs. Lorem ipsum
                  dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                  tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
                  minim veniam, quis nostrud exercitation ullamco laboris nisi
                  ut aliquip ex ea commodo consequat.
                </p>
             
               
              </div>{" "}
              {/* col.// */}
              <aside className="col-md-4">
                <div className="box">
                  <h5 className="title-description">More from This Brand</h5>
                  {brandProducts.map((product) => (
                     <article className="media mb-3">
                       <Link to={`/${product.product_id}`}>
                {getProductImage(product.product_id).map((image, index) => (
                        <img className="img-sm mr-3"
                          key={index}
                          src={`${image.image_path}`}
      
                        />
                      ))}
                </Link>
                     <div className="media-body">
                       <h6 className="mt-0">
                         <a href="#">{product.product_name}</a>
                       </h6>
                       <p className="mb-2">
                       {formatCurrency(product.regular_price)}
                       </p>
                     </div>
                   </article>
                  )).slice(0, 4)}
                  
                </div>{" "}
                {/* box.// */}
              </aside>{" "}
              {/* col.// */}
            </div>{" "}
            {/* row.// */}
          </div>{" "}
          {/* container .//  */}
        </section>
        {/* ========================= SECTION CONTENT END// ========================= */}
        {/* ========================= SECTION SUBSCRIBE  ========================= */}
        <section className="padding-y-lg bg-light border-top">
          <div className="container">
            <p className="pb-2 text-center">
              Delivering the latest product trends and industry news straight to
              your inbox
            </p>
            <div className="row justify-content-md-center">
              <div className="col-lg-4 col-sm-6">
                <form className="form-row">
                  <div className="col-8">
                    <input
                      className="form-control"
                      placeholder="Your Email"
                      type="email"
                    />
                  </div>{" "}
                  {/* col.// */}
                  <div className="col-4">
                    <button type="submit" className="btn btn-block btn-warning">
                      {" "}
                      <i className="fa fa-envelope" /> Subscribe{" "}
                    </button>
                  </div>{" "}
                  {/* col.// */}
                </form>
                <small className="form-text">
                  We’ll never share your email address with a third-party.{" "}
                </small>
              </div>{" "}
              {/* col-md-6.// */}
            </div>
          </div>
        </section>
        {/* ========================= SECTION SUBSCRIBE END// ========================= */}
      </>
    </div>
  );
};

export default DetailPro;
